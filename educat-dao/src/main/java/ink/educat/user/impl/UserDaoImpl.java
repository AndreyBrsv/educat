package ink.educat.user.impl;

import com.google.common.base.Preconditions;
import ink.educat.user.api.Entities.User;
import ink.educat.user.api.UserDao;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    /**
     * {@inheritDoc}
     */
    @Nullable
    public User getUserByEmail(@NonNull final String email) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                email != null && !email.isEmpty(),
                "Email argument can't be empty!");

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isUserExists(@NonNull String email) {
        //noinspection ConstantConditions
        if (email == null || email.isEmpty()) {
            return false;
        }

        return false;
    }
}
