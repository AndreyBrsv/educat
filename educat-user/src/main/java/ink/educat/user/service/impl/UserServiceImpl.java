package ink.educat.user.service.impl;

import com.google.common.base.Preconditions;
import ink.educat.user.dao.api.UserDao;
import ink.educat.user.dao.api.entities.ShortDetailedUser;
import ink.educat.user.dao.api.entities.User;
import ink.educat.user.dao.api.entities.UserStatus;
import ink.educat.core.service.api.DeleteStrategy;
import ink.educat.user.service.api.UserService;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Сервис для работы с пользователями. Для управления пользователями
 * используйте его, а не {@link UserDao}.
 *
 * @see UserDao
 */
@Service
public class UserServiceImpl implements UserService {

    // Паттерн для проверки email RFC 5322
    private static final String EMAIL_TEMPLATE = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private final UserDao userDao;
    private final Pattern emailPattern = Pattern.compile(EMAIL_TEMPLATE, Pattern.CASE_INSENSITIVE);

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public User getUserByEmail(@NonNull String email) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                email != null && !email.isEmpty(),
                "Email can't be null or empty!"
        );
        // Относительно многопоточного доступа к этому методу, тут можно не
        // волноваться, так как java.util.regex.Pattern.matcher - потокобезопасный метод.
        // Внутри у него synchronized.
        final Matcher matcher = emailPattern.matcher(email);
        Preconditions.checkState(
                matcher.find(),
                "Invalid email address format!"
        );
        return userDao.getUserByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public ShortDetailedUser getShortDetailedUserById(final long userId) {
        return userDao.getShortDetailedUserById(userId);
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
