package ink.educat.user.api;

import ink.educat.user.api.Entities.User;

/**
 * Сервис для проверки прав доступа пользователей.
 * <p>
 * Как использовать?
 * Какждый фасадный метод(тот, который принимает и обрабатывает запросы)
 * должен иметь свое право доступа. Под правом доступа подразумевается
 * некоторая уникальная строка, добавленная в таблицу EC_USER_PERMISSIONS.
 * Например, пусть имеется способ получения списка курсов www.educat.ink/courses,
 * пусть на стороне сервера этот запрос обрабатывает некоторый сервлет в методе doGet(...),
 * который может выглядеть к примеру так:
 * <p>
 * doGet(HttpRequest request, HttpResponse response) {
 *   // Тогда во время выполнения этого метода
 *   // нужно получить User, который выполнил этот запрос
 *   User user = ...
 *
 *   // Предварительно в базу мы сделали:
 *   // INSERT INTO EC_USER_PERMISSIONS
 *   // VALUES (1, 'get_all_courses', 'Право на получения списка курсов');
 *   // После того как все сервисы поднимаются все эти пермишены кладутся
 *   // в ink.educat.user.Impl.UserPermissionServiceImpl#userPermissionsMap
 *
 *   // Теперь, чтобы проверить права доступа, достаточно выполнить guard(...)
 *
 *   guard(user, "get_all_courses");
 *
 *   // Если все ок, то метод просто выполнится, иначе упадем
 *   // с исключением ink.educat.user.api.NotEnoughPermissionsException
 *   // обернем его и вернем пользователю, мол у него недостаточно прав
 * }
 */
public interface UserPermissionService {
     
     /**
     * Метод, определяющий разрешен ли доступ к данному пермишену
     * @param user - пользователь
     * @param permission - название пермишена для которого требуется проверка
     * @throws NotEnoughPermissionsException если у роли пользователя нет данных прав.
     */
     void guardian(User user, String permission);
}

