package ink.educat.user.api;

import ink.educat.user.api.Entities.User;

/**
 * Сервис для проверки прав доступа пользователей
 */
public interface UserPermissionService {
     
     /**
     * Метод, определяющий разрешен ли доступ к данному пермишену
     * @param user - пользователь
     * @param permission - название пермишена для которого требуется проверка
     * @return true, если пользователь обладает данным пермишеном, false иначе
     */
     //TODO: переделать на void с генерацией специального исключения
     boolean isAccessAllowed(User user, String permission);
}
