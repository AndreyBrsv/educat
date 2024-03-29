package ink.educat.user.service.api;

import ink.educat.core.service.api.DeleteStrategy;
import ink.educat.user.dao.api.entities.ShortDetailedUser;
import ink.educat.user.dao.api.entities.User;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface UserService {

    /**
     * Позволяет получить пользователя по email
     *
     * @param email - адрес электронной почты пользователя
     * @return - пользователя, соответствующего данному email,
     * или null, если пользователь не найден 
     */
    @NonNull
    User getUserByEmail(@NonNull String email);

    /**
     * Метод, позволяющий получить "укороченную" версию пользователя. Подходит, например,
     * когда один пользователь заходит на профиль другого, чтобы подписаться.
     *
     * @param userId - уникальный идентификатор пользователя
     * @return - информацию о пользователе
     * @see ShortDetailedUser
     */
    ShortDetailedUser getShortDetailedUserById(long userId);

    /**
     * Метод, позволяющий удалить пользователя;
     *
     * @param user           - пользователь
     * @param deleteStrategy - стратегия удаления
     * @see DeleteStrategy
     */
    void deleteWithStrategy(@NonNull User user, @NonNull DeleteStrategy deleteStrategy);
}
