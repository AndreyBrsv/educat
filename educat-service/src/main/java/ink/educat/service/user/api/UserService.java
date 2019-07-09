package ink.educat.service.user.api;

import ink.educat.core.api.DeleteStrategy;
import ink.educat.dao.user.api.Entities.ShortDetailedUser;
import ink.educat.dao.user.api.Entities.User;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface UserService {

    /**
     * Позволяет получить пользователя по email
     *
     * @param email - адрес электронной почты пользователя
     * @return - пользователя, соответствующего данному email,
     * или null, если пользователь не найден 
     */
    @Nullable
    User getUserByEmail(@NonNull String email) throws IllegalArgumentException;

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
