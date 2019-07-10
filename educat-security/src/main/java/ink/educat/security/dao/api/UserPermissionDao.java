package ink.educat.security.dao.api;

import java.util.Map;

/**
 * Объект доступа к данным для работы с правами пользователей.
 * Для дополнительной информации смотреть таблицы: EC_USER_ROLES, EC_USER_PERMISSIONS.
 */
public interface UserPermissionDao {

    /**
     * Загружает роли и права из базы данных.
     *
     * @return Возвращает Map<String, String>, где ключ это название пермишена,
     * а значение - список ролей, для которых этот пермишен доступен.
     */
    Map<String,String> loadRolesAndPermissions();

}