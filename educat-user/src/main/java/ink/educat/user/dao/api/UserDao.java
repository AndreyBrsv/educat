package ink.educat.user.dao.api;

import ink.educat.core.dao.api.AbstractDao;
import ink.educat.user.dao.api.entities.ShortDetailedUser;
import ink.educat.user.dao.api.entities.User;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Интерфейс доступа к данным пользователей.
 */
public interface UserDao extends AbstractDao<User> {

    /**
     * Позволяет получить пользователя по email
     *
     * @param email - адрес электронной почты пользователя
     * @return экземпляр класса {@link User} если найден
     * @throws UserNotFoundException - если пользователь найден не был
     */
    @NonNull
    User findUserByEmail(@NonNull String email);


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
