package ink.educat.user.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import ink.educat.user.api.Entities.User;
import ink.educat.user.api.Entities.UserRole;
import ink.educat.user.api.Entities.UserStatus;
import ink.educat.user.api.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    // Поля для @Autowired, не забывать добавлять в конструктор
    private final JdbcTemplate jdbcTemplate;

    // Мапперы
    /**
     * Использовать данный маппер везде в {@link UserDaoImpl} в методах,
     * где требуется вернуть пользователя.
     */
    private final RowMapper<User> userRowMapper = ((resultSet, i) -> {
        final UserRole userRole = UserRole.parseByName(resultSet.getString("role_name"));
        final UserStatus userStatus = UserStatus.parseByName(resultSet.getString("status"));
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("login"),
                resultSet.getString("email"),
                resultSet.getString("first_name"),
                resultSet.getString("second_name"),
                userStatus,
                userRole
        );
    });

    private Map<String, Object> userMapper(User user) {
        Map<String, Object> values = new ManagedMap<>();
            values.put("user_id", user.getId());
            values.put("login", user.getLogin());
            values.put("pass", user.getPass());
            values.put("email", user.getEmail());
            values.put("first_name", user.getFirstName());
            values.put("second_name", user.getSecondName());
            values.put("UserStatus", user.getUserStatus());
            values.put("UserRole", user.getUserRole());
            values.put("UserRoleId", user.getUserRole().getCode());

        return values;
    }

    //TODO реализовать метод для валидации входных параметров на обновление и создание пользователя

    @Autowired
    public UserDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    public User getUserByEmail(@NonNull final String email) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                email != null && !email.isEmpty(),
                "Email argument can't be empty!");

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("email", email);

        final List<User> userList = jdbcTemplate.query(
                "SELECT DISTINCT * FROM EC_USERS U \n" +
                        "LEFT JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
                        "WHERE EMAIL = :email",
                userRowMapper,
                mapSqlParameterSource
        );

        if (userList.isEmpty()) {
            return null;
        }

        Preconditions.checkState(
                userList.size() == 1,
                "Found multiple users with this email!"
        );

        return userList.iterator().next();

    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    public User findById(@NonNull final long id) {
        Preconditions.checkArgument(
                id > 0,
                "ID must be > 0!");

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("user_id", id);

        final List<User> userList = jdbcTemplate.query(
                "SELECT DISTINCT * FROM EC_USERS U \n" +
                        "LEFT JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
                        "WHERE USER_ID = :user_id",
                userRowMapper,
                mapSqlParameterSource
        );

        if(userList.isEmpty()) {
            return null;
        }

        Preconditions.checkState(
                userList.size() == 1,
                "Found multiple users with this id!"
        );

        return userList.iterator().next();
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    public List<User> findByIDs(@NonNull final Iterable<Long> ids) {
        final StringBuilder validIds = new StringBuilder();
        Long id;

        if(ids.iterator().hasNext()) {
            id = ids.iterator().next();

            if(id > 0) {
                validIds.append(id);
            }
        }

        while (ids.iterator().hasNext()) {
            id = ids.iterator().next();

            if (id > 0) {
                validIds.append(", ").append(id);
            }
        }

        Preconditions.checkArgument(
                validIds.toString().equals(""),
                "No valid ids!"

        );

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("user_ids", validIds);

        final List<User> userList = jdbcTemplate.query(
                "SELECT DISTINCT * FROM EC_USERS U \n" +
                        "LEFT JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
                        "WHERE USER_ID IN (:user_ids)",
                userRowMapper,
                mapSqlParameterSource
        );

        if(userList.isEmpty()) {
            return null;
        }

        return userList;
    }

    /**
     * {@inheritDoc}
     */
    public void Update(@NonNull final Iterable<User> users) {
        //TODO https://stackoverflow.com/questions/20360574/why-springs-jdbctemplate-batchupdate-so-slow
        //TODO выбрать вариант
    }

    /**
     * {@inheritDoc}
     */
    public void saveOrUpdate(@NonNull final User user) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                user != null,
                "User can't be null!");

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValues(userMapper(user));

        jdbcTemplate.update(
                "INSERT INTO EC_USERS \n" +
                        "(user_role_id, email, login, pass, first_name, second_name, status) \n" +
                        "VALUES " +
                        "(:UserRoleId, :email, :login, :pass, :first_name, :second_name, :UserStatus) \n" +
                        "ON CONFLICT (USER_ID)" +
                        "DO UPDATE SET \n" +
                        "login = :login, email = :email, \n" +
                        "first_name = :first_name, second_name = :second_name, \n" +
                        "user_status = :user_status, user_role = :user_role \n",
                mapSqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(@NonNull final User user) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                user != null,
                "User can't be null!");

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValues(userMapper(user));

        if(user.getUserStatus() == UserStatus.DELETED)
            jdbcTemplate.queryForMap(
                    "DELETE FROM EC_USERS WHERE USER_ID = :id",
                    mapSqlParameterSource);
    }

    @Override
    public void saveOrUpdate(Iterable<User> users) {

    }
}
