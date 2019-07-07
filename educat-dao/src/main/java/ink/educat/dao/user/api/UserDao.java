package ink.educat.dao.user.api;

import ink.educat.core.api.AbstractDao;
import ink.educat.dao.user.api.Entities.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Интерфейс доступа к данным пользователей
 */
public interface UserDao extends AbstractDao<User> {

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

}
