package ink.educat.service.impl;

import ink.educat.dao.api.SecurityDao;
import ink.educat.service.api.SecurityService;
import ink.educat.service.api.UnidentifiedSecurityParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final ConcurrentHashMap<String, String> securityParameters;
    private final SecurityDao securityDao;

    @Autowired
    public SecurityServiceImpl(final SecurityDao securityDao) {
        this.securityDao = securityDao;
        this.securityParameters = securityDao.loadSecurities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValueFor(String key) {
        if (securityParameters.get(key) == null) {
            throw new UnidentifiedSecurityParameterException();
        }
        return securityParameters.get(key);
    }
}
