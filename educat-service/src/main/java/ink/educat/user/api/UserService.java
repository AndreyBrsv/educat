package ink.educat.user.api;

import ink.educat.user.api.Entities.User;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface UserService {
    /**
     * Позволяет получить пользователя по email
     * @param email - адрес электронной почты пользователя
     * @return
     */
    @Nullable
    User getUserByEmail(@NonNull String email);
}
