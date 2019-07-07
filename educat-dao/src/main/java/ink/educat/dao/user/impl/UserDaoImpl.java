package ink.educat.dao.user.impl;

import com.google.common.base.Preconditions;
import ink.educat.dao.user.api.Entities.User;
import ink.educat.dao.user.api.Entities.UserRole;
import ink.educat.dao.user.api.Entities.UserStatus;
import ink.educat.dao.user.api.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    // Поля для @Autowired, не забывать добавлять в конструктор
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // Мапперы
    /**
     * Использовать данный маппер везде в {@link UserDaoImpl} в методах,
     * где требуется вернуть пользователя.
     */
    private final RowMapper<User> userRowMapper = ((resultSet, i) -> new User(
            resultSet.getLong("user_id"),
            resultSet.getString("email"),
            resultSet.getBoolean("email_confirmed"),
            resultSet.getString("first_name"),
            resultSet.getString("second_name"),
            UserStatus.parseByName(resultSet.getString("status")),
            UserRole.parseByName(resultSet.getString("role_name")),
            resultSet.getTimestamp("registration_date").toLocalDateTime()
    ));

    private Map<String, Object> userMapper(User user) {
        Map<String, Object> values = new ManagedMap<>();
            values.put("user_id", user.getId());
            values.put("pass", user.getPass());
            values.put("email", user.getEmail());
            values.put("first_name", user.getFirstName());
            values.put("second_name", user.getSecondName());
            values.put("UserStatus", user.getUserStatus());
            values.put("UserRole", user.getUserRole());
            values.put("UserRoleId", user.getUserRole().getCode());

        return values;
    }

    @Autowired
    public UserDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

        final List<User> userList = namedParameterJdbcTemplate.query(
                "SELECT * FROM EC_USERS U \n" +
                        "JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
                        "WHERE EMAIL = :email",
                mapSqlParameterSource,
                userRowMapper
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
    public User findById(final long id) {
        Preconditions.checkArgument(
                id > 0,
                "ID must be > 0!");

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("user_id", id);

        final List<User> userList = namedParameterJdbcTemplate.query(
                "SELECT * FROM EC_USERS U \n" +
                        "JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
                        "WHERE USER_ID = :user_id",
                mapSqlParameterSource,
                userRowMapper
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

        final List<User> userList = namedParameterJdbcTemplate.query(
                "SELECT * FROM EC_USERS U \n" +
                        "JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
                        "WHERE USER_ID IN (:user_ids)",
                mapSqlParameterSource,
                userRowMapper
        );

        if(userList.isEmpty()) {
            return null;
        }

        return userList;
    }

    /**
     * {@inheritDoc}
     */
    public void update(@NonNull final Iterable<User> users) {
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

        namedParameterJdbcTemplate.update(
                "INSERT INTO EC_USERS \n" +
                        "(user_role_id, email, password, first_name, second_name, status) \n" +
                        "VALUES " +
                        "(:UserRoleId, :email, :pass, :first_name, :second_name, :UserStatus) \n" +
                        "ON CONFLICT (USER_ID)" +
                        "DO UPDATE SET \n" +
                        "email = :email, \n" +
                        "first_name = :first_name, second_name = :second_name, \n" +
                        "status = :user_status, \n" +
                        "user_role_id = (SELECT DISTINCT user_role_id FROM ec_user_roles WHERE user_role_id = :user_role)",
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
            namedParameterJdbcTemplate.queryForMap(
                    "DELETE FROM EC_USERS WHERE USER_ID = :id",
                    mapSqlParameterSource);
    }
}
