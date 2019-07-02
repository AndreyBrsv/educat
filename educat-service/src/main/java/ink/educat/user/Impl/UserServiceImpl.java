package ink.educat.user.Impl;

import ink.educat.user.api.Entities.User;
import ink.educat.user.api.UserDao;
import ink.educat.user.api.UserService;
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
}
