package ink.educat.user.api.Entities;

import com.google.common.base.Preconditions;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Сущность, отражающая пользователя в системе.
 *
 * @author Andrey Borisov
 * @author Ilya Mikheev
 *
 * @see UserStatus
 * @see UserRole
 *
 */
//TODO: После устаканивания структуры данного pojo,
//TODO: определить методы toString(), hashCode(), equals(User user)
public class User implements Serializable {

    private long id;

    private String login;

    private String pass;

    @NonNull
    private String email;

    @Nullable
    private String firstName;

    @Nullable
    private String secondName;

    @NonNull
    private UserStatus userStatus;

    @NonNull
    private UserRole userRole;

    //private LocalDate registrationDate;
    //private LocalDate lastLoginDate;

    public User(final long id,
                @Nullable final String login,
                @NonNull final String email,
                @Nullable final String firstName,
                @Nullable final String secondName,
                @NonNull final UserStatus userStatus,
                @NonNull final UserRole userRole
    ) {

        //noinspection ConstantConditions
        Preconditions.checkArgument(
                email != null && !email.isEmpty(),
                "Email can't be null or empty!");
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                userStatus != null,
                "UserStatus can't be null!");
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                userRole != null,
                "UserRole can't be null!");

        this.id = id;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userStatus = userStatus;
        this.userRole = userRole;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPass() { return pass; }

    public void setPass(String pass) { this.pass = pass; }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                userStatus != null && !email.isEmpty(),
                "Email can't be null!");
        this.email = email;
    }

    @NonNull
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(@NonNull UserRole userRole) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                userStatus != null,
                "UserRole can't be null!");
        this.userRole = userRole;
    }

    @NonNull
    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(@NonNull UserStatus userStatus) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                userStatus != null,
                "UserStatus can't be null!");
        this.userStatus = userStatus;
    }
}
