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

import java.util.Iterator;
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
            resultSet.getString("password"),
            resultSet.getString("first_name"),
            resultSet.getString("second_name"),
            UserStatus.parseByName(resultSet.getString("status")),
            UserRole.parseByName(resultSet.getString("role_name")),
            resultSet.getTimestamp("registration_date").toLocalDateTime()
    ));

    private Map<String, Object> userMapper(User user) {
        Map<String, Object> values = new ManagedMap<>();
            values.put("user_id", user.getId());
            values.put("password", user.getPassword());
            values.put("email", user.getEmail());
            values.put("first_name", user.getFirstName());
            values.put("second_name", user.getSecondName());
            values.put("user_status", user.getUserStatus());
            values.put("user_role", user.getUserRole());
            values.put("user_role_id", user.getUserRole().getCode());

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

        Preconditions.checkState(
                !userList.isEmpty(),
                "No user found with given email!"
        );

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
        final Iterator iterator = ids.iterator();
        Long id;

        while (iterator.hasNext()) {
            id = ids.iterator().next();
            if (id > 0) {
                validIds.append(id);
            }
            if (iterator.hasNext()) {
                validIds.append(", ");
            }
        }

        Preconditions.checkState(
                !validIds.toString().isEmpty(),
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
                        "(USER_ROLE_ID, EMAIL, PASSWORD, FIRST_NAME, SECOND_NAME, STATUS) \n" +
                        "VALUES " +
                        "(:user_role_id, :email, :password, :first_name, :second_name, :user_status) \n" +
                        "ON CONFLICT (USER_ID)" +
                        "DO UPDATE SET \n" +
                        "EMAIL = :email, \n" +
                        "FIRST_NAME = :first_name, SECOND_NAME = :second_name, \n" +
                        "STATUS = :user_status, \n" +
                        "USER_ROLE_ID = (SELECT DISTINCT USER_ROLE_ID FROM EC_USER_ROLES WHERE USER_ROLE_ID = :user_role)",
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
