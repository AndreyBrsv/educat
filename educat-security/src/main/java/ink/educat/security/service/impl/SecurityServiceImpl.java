package ink.educat.security.service.impl;

import ink.educat.security.dao.api.SecurityDao;
import ink.educat.security.service.api.SecurityService;
import ink.educat.security.service.api.UnidentifiedSecurityParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Сервис для управления параметрами безопасности. См. таблицу EC_SECURITIES.
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    /**
     * @discussion Конечно, мы можем использовать и обычную HashMap
     * если уверены, что данную карту после инициализации будут только
     * читать, однако мы не уверены :), но на данный момент - это так.
     * Параметры безопасности загружаются из базы один раз при старте сервера.
     * Полагем, что в будущем может возникнуть потребность в перезагрузке
     * данной карты в Runtime, поэтому сделали ConcurrentHashMap.
     * @see ink.educat.core.service.api.Refreshable
     */
    private ConcurrentHashMap<String, String> securityParameters;
    private final SecurityDao securityDao;

    @Autowired
    public SecurityServiceImpl(final SecurityDao securityDao) {
        this.securityParameters = securityDao.loadSecurities();
        this.securityDao = securityDao;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        securityParameters = securityDao.loadSecurities();
    }
}
