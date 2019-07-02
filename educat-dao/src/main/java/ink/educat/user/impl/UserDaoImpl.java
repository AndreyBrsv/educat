package ink.educat.user.impl;

import com.google.common.base.Preconditions;
import ink.educat.user.api.Entities.User;
import ink.educat.user.api.Entities.UserRole;
import ink.educat.user.api.Entities.UserStatus;
import ink.educat.user.api.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
