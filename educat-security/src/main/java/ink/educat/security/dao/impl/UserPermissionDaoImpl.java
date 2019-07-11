package ink.educat.security.dao.impl;

import ink.educat.security.dao.api.UserPermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Объект доступа к данным для работы с правами пользователей.
 *
 * @see UserPermissionDao
 */
@Repository
public class UserPermissionDaoImpl implements UserPermissionDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserPermissionDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public ConcurrentHashMap<String, String> loadRolesAndPermissions() {
        // Написать имплементацию метода
        return new ConcurrentHashMap<>();
    }
}
