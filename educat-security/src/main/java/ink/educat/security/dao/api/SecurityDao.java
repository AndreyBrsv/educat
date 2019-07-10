package ink.educat.security.dao.api;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Объект доступа к данным для работы с параметрами безопасности.
 */
public interface SecurityDao {

    /**
     * Загружает параметры безопасности из базы данных
     *
     * @return - потокобезопасную карту
     */
    ConcurrentHashMap<String, String> loadSecurities();
}
