package ink.educat.service.user.impl;

import com.google.common.base.Preconditions;
import ink.educat.core.api.DeleteStrategy;
import ink.educat.dao.user.api.Entities.User;
import ink.educat.dao.user.api.Entities.UserStatus;
import ink.educat.dao.user.api.UserDao;
import ink.educat.service.user.api.UserService;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с пользователями. Для управления пользователями
 * используйте его, а не {@link UserDao}.
 *
 * @see UserDao
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public User getUserByEmail(@NonNull String email) {
        // Тут провести валидацию формата email
        // на основе метода match и регулярных выражений,
        // чтобы в случае чего упасть раньше, чем метод
        // полезет в базу.
        return userDao.getUserByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteWithStrategy(@NonNull final User user, @NonNull final DeleteStrategy deleteStrategy) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                user != null,
                "User can't be null!");
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                deleteStrategy != null,
                "Delete strategy can't be null!");
        switch (deleteStrategy) {
            case NAIVE:
                userDao.delete(user);
                break;
            case CHANGE_STATUS:
                user.setUserStatus(UserStatus.DELETED);
                userDao.saveOrUpdate(user);
                break;
            default:
                throw new IllegalStateException("Unsupported delete strategy!");
        }
    }
}