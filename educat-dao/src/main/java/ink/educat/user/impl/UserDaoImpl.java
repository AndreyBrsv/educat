package ink.educat.user.impl;

import ink.educat.user.api.Entities.User;
import ink.educat.user.api.UserDao;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Nullable
    public User getUserByEmail(@NonNull final String email) {
        return null;
    }
}
