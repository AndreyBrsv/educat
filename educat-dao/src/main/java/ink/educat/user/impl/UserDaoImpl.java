package ink.educat.user.impl;

import com.google.common.base.Preconditions;
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


    private final Map<String, String> userMapper(User user) {
        Map<String, String> values = new ManagedMap<>();
            values.put("user_id", Long.toString(user.getId()));
            values.put("login", user.getLogin());
            values.put("email", user.getEmail());
            values.put("first_name", user.getFirstName());
            values.put("second_name", user.getSecondName());
            values.put("UserStatus", user.getUserStatus().getDisplayableName());
            values.put("UserRole", user.getUserRole().getDisplayableName());

        return values;
    }

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

        //TODO maybe .size() > 1?
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
                        "WHERE ID = :user_id",
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
        StringBuilder validIds = new StringBuilder();
        Long id;

        if(ids.iterator().hasNext()) {
            id = ids.iterator().next();

            if(id > 0) {
                validIds.append(ids.iterator().next());
            }
        }

        while (ids.iterator().hasNext()) {
            id = ids.iterator().next();

            if (id > 0) {
                validIds.append(", " + ids.iterator().next());
            }
        }

        Preconditions.checkArgument(
                validIds.equals(""),
                "No valid ids!"

        );

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("user_ids", validIds);

        final List<User> userList = jdbcTemplate.query(
                "SELECT DISTINCT * FROM EC_USERS U \n" +
                        "LEFT JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
                        "WHERE ID IN (:user_ids)",
                userRowMapper,
                mapSqlParameterSource
        );

        if(userList.isEmpty()) {
            return null;
        }

        return userList;
    }

    //TODO ? сделать отдельный метод для валидации всех параметров. Либо это на клиенте?
    //TODO ? использовать апдейт для одного юзера или есть спецаильный удобный апдейт всех?
    /**
     * {@inheritDoc}
     */
    public void saveOrUpdate(@NonNull final Iterable<User> users) {
        while(users.iterator().hasNext())
        {
            saveOrUpdate(users.iterator().next());
        }

        //jdbcTemplate.batchUpdate();
    }

    /**
     * {@inheritDoc}
     */
    public void saveOrUpdate(@NonNull final User user) {
        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValues(userMapper(user));

        jdbcTemplate.update(
                "UPDATE EC_USERS SET " +
                        "login = :login, email = :email, \n" +
                        "first_name = :first_name, second_name = :second_name, \n" +
                        "user_status = :user_status, user_role = :user_role \n" +
                        "WHERE id = :user_id",
                mapSqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(@NonNull final User user) {
        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValues(userMapper(user));

        jdbcTemplate.queryForMap(
                "DELETE FROM EC_USERS WHERE id = :id",
                mapSqlParameterSource);
    }
}
