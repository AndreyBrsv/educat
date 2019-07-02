package ink.educat.user.api;

import ink.educat.user.api.Entities.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Интерфейс доступа к данным пользователей
 */
public interface UserDao {

    /**
     * Позволяет получить пользователя по email
     * @param email - адрес электронной почты пользователя
     * @return null - в случае, если пользователь не найден и
     * экземпляр класса {@link User} если найден.
     */
    //TODO: вместо возвращения null нужно сделать бросок
    //TODO: специального исключения, которое будет перехвачено
    //TODO: на более высоком уровне и обернуто в http response
    @Nullable
    User getUserByEmail(@NonNull String email);

    /**
     * Позволяет узнать, существует ли пользователь в системе
     *
     * @param email - адрес электронной почты пользователя
     * @return true, если пользователь найден, false иначе
     */
    boolean isUserExists(@NonNull String email);

}
