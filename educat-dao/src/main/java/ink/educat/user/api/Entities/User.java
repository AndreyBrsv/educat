package ink.educat.user.api.Entities;

import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Сущность, отражающая пользователя в системе.
 *
 * @author Andrey Borisov
 * @author Ilya Mikheev
 *
 * @see UserStatus
 *
 */
//TODO: После устаканивания структуры данного pojo,
//TODO: определить методы toString(), hashCode(), equals(User user)
public class User implements Serializable {

    private long id;

    private String login;

    @Nullable
    private String email;

    @Nullable
    private String firstName;

    @Nullable
    private String secondName;

    @NonNull
    private UserStatus userStatus;

    public User() {

    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Nullable String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(@Nullable String secondName) {
        this.secondName = secondName;
    }

    @NonNull
    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(@NonNull UserStatus userStatus) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                userStatus != null,
                "UserStatus name can't be empty!");
        this.userStatus = userStatus;
    }
}
