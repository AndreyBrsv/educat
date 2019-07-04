package ink.educat.user.api;

import java.util.Map;

public interface UserPermissionDao {

    /**
     * Загружает роли и права из базы данных.
     *
     * @return Возвращает Map<String, String>, где ключ это название пермишена,
     * а значение - список ролей, для которых этот пермишен доступен.
     */
    Map<String,String> loadRolesAndPermissions();

}
