package ink.educat.user.api;

import ink.educat.core.api.DeleteStrategy;
import ink.educat.user.api.Entities.User;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface UserService {

    /**
     * Позволяет получить пользователя по email
     * @param email - адрес электронной почты пользователя
     * @return - пользователя, соответствующего данному email,
     * или null, если пользователь не найден 
     */
    @Nullable
    User getUserByEmail(@NonNull String email);

    /**
     * Метод, позволяющий удалить пользователя;
     *
     * @param user - пользователь
     * @param deleteStrategy - стратегия удаления
     */
    void deleteWithStrategy(@NonNull User user, @NonNull DeleteStrategy deleteStrategy);
}
