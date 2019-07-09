package ink.educat.dao.user.api;

import ink.educat.core.api.AbstractDao;
import ink.educat.dao.user.api.Entities.ShortDetailedUser;
import ink.educat.dao.user.api.Entities.User;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Интерфейс доступа к данным пользователей
 */
public interface UserDao extends AbstractDao<User> {

    /**
     * Позволяет получить пользователя по email
     *
     * @param email - адрес электронной почты пользователя
     * @return null - в случае, если пользователь не найден и
     * экземпляр класса {@link User} если найден.
     */
    @NonNull
    User getUserByEmail(@NonNull String email);


    /**
     * Позволяет получить краткую информацию о пользователе
     *
     * @param id - уникальный идентификатор пользователя
     * @return - краткую информацию о пользователе
     * @throws UserNotFoundException, если пользователь не найден
     *
     * @see ShortDetailedUser
     */
    @NonNull
    ShortDetailedUser getShortDetailedUserById(long id);

}
