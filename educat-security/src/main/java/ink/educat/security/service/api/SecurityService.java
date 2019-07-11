package ink.educat.security.service.api;

import ink.educat.core.service.api.Refreshable;

/**
 * Сервис для управления параметрами безопасности. См. таблицу EC_SECURITIES.
 *
 */
public interface SecurityService extends Refreshable {

    /**
     * Позволяет получить значение параметра безопасности по ключу
     *
     * @param key - ключ
     * @return значение параметра безопасности
     */
    String getValueFor(String key);

}
