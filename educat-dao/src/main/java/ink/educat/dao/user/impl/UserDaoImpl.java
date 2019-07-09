package ink.educat.dao.user.impl;

import com.google.common.base.Preconditions;
import ink.educat.dao.user.api.Entities.ShortDetailedUser;
import ink.educat.dao.user.api.Entities.User;
import ink.educat.dao.user.api.Entities.UserRole;
import ink.educat.dao.user.api.Entities.UserStatus;
import ink.educat.dao.user.api.UserDao;
import ink.educat.dao.user.api.UserNotFoundException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    // Поля для @Autowired, не забывать добавлять в конструктор
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Мапперы из БД в Java
     * <p>
     * Использовать данный маппер везде в {@link UserDaoImpl} в методах,
     * где требуется вернуть пользователя.
     */
    private final RowMapper<User> userRowMapper = ((resultSet, i) ->
            new User(
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

    /**
     * Использовать данный маппер везде в {@link UserDaoImpl} в методах,
     * где требуется вернуть краткую информацию о пользователе.
     */
    private final RowMapper<ShortDetailedUser> shortDetailedUserRowMapper = ((resultSet, i) ->
            new ShortDetailedUser(
                    resultSet.getLong("user_id"),
                    resultSet.getString("user_name"),
                    resultSet.getString("user_info"),
                    resultSet.getString("avatar_link"),
                    resultSet.getLong("publications_count"),
                    resultSet.getLong("subscribers")
            ));

    /**
     * Мапперы из Java в БД
     */
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

    // Методы DAO
    /**
     * {@inheritDoc}
     */
    @NonNull
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
            throw new  UserNotFoundException("User with given email not found!");
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
    @NonNull
    public ShortDetailedUser getShortDetailedUserById(final long id) {
        Preconditions.checkArgument(
                id > 0,
                "ID must be > 0!"
        );

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource("user_id", id);

        final List<ShortDetailedUser> shortDetailedUsers = namedParameterJdbcTemplate.query(
                "SELECT U.USER_ID AS user_id, \n" +
                        "    CONCAT(U.FIRST_NAME, ' ', U.SECOND_NAME) AS user_name,\n" +
                        "    AUR.USER_INFO AS user_info,\n" +
                        "    AUR.AVATAR_LINK AS avatar_reference,\n" +
                        "    COUNT(A.ARTICLE_ID) AS publications_count,\n" +
                        "    COUNT(UTUS.SUBSCRIBED_USER_ID) AS subscribers\n" +
                        "    FROM EC_USERS U\n" +
                        "    LEFT JOIN EC_ADDITIONAL_USER_REFERENCES AUR ON AUR.USER_ID = U.USER_ID\n" +
                        "    LEFT JOIN EC_ARTICLES A ON A.USER_ID = U.USER_ID\n" +
                        "    LEFT JOIN EC_USER_TO_USER_SUBSCRIPTIONS UTUS ON UTUS.SUBSCRIBE_USER_ID = U.USER_ID\n" +
                        "WHERE U.USER_ID = :user_id\n" +
                        "GROUP BY (U.user_id, user_name, user_info, avatar_link)",
                mapSqlParameterSource,
                shortDetailedUserRowMapper
        );

        if (shortDetailedUsers.isEmpty()) {
            throw new UserNotFoundException("User with given id not found!");
        }

        Preconditions.checkState(
                shortDetailedUsers.size() == 1,
                "Found multiple users with this email!"
        );

        return shortDetailedUsers.iterator().next();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
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

        if (userList.isEmpty()) {
            throw new UserNotFoundException("User with given id not found!");
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
        final Iterator<Long> iterator = ids.iterator();
        Long id;

        while (iterator.hasNext()) {
            id = iterator.next();
            if (id > 0) {
                validIds.append(id);
                if (iterator.hasNext()) {
                    validIds.append(", ");
                }
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

        if (userList.isEmpty()) {
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

        namedParameterJdbcTemplate.queryForMap(
                "DELETE FROM EC_USERS WHERE USER_ID = :id",
                mapSqlParameterSource);
    }
}
