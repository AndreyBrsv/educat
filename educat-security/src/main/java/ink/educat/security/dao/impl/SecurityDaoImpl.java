package ink.educat.security.dao.impl;

import ink.educat.security.dao.api.SecurityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Объект доступа к данным для работы с параметрами безопасности.
 *
 * @see SecurityDao
 */
@Repository
public class SecurityDaoImpl implements SecurityDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public SecurityDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ConcurrentHashMap<String, String> loadSecurities() {

//        return new ConcurrentHashMap<String, String>(
//                (Map) namedParameterJdbcTemplate.queryForMap(
//                        "SELECT SECURITY_NAME, SECURITY_VALUE FROM EC_SECURITIES",
//                        new HashMap<String, String>())
//        );
        return new ConcurrentHashMap<String, String>();
    }
}
