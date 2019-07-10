package ink.educat.service.api;

public interface SecurityService {

    /**
     * Позволяет получить значение параметра безопасности по ключу
     *
     * @param key - ключ
     * @return значение параметра безопасности
     */
    String getValueFor(String key);

}
