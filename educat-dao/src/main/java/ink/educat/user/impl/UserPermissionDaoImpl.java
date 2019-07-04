package ink.educat.user.impl;

import ink.educat.user.api.UserPermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserPermissionDaoImpl implements UserPermissionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserPermissionDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, String> loadRolesAndPermissions() {
        // Написать имплементацию метода
        return new HashMap<>();
    }
}
