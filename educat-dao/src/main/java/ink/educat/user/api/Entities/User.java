package ink.educat.user.api.Entities;

import com.google.common.base.Preconditions;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    /** Id пользователя */
    private long id;

    /** Пароль пользователя */
    private String password;

    /** Электронная почта пользователя */
    @NonNull
    private String email;

    /** Признак подтвержденности адреса электронной почты */
    private boolean isEmailConfirmed;

    /** Имя пользователя */
    @NonNull
    private String firstName;

    /** Фамилия пользователя */
    @NonNull
    private String secondName;

    /** Отчество пользователя(опционально) */
    @Nullable
    private String middleName;

    /** Статус пользователя */
    @NonNull
    private UserStatus userStatus;

    /** Роль пользователя */
    @NonNull
    private UserRole userRole;

    /** Дата регистрации */
    @NonNull
    private LocalDateTime registrationDate;
    //private LocalDate lastLoginDate;

    public User(final long id,
                @NonNull final String email,
                final boolean isEmailConfirmed,
                @NonNull final String firstName,
                @NonNull final String secondName,
                @NonNull final UserStatus userStatus,
                @NonNull final UserRole userRole,
                @NonNull final LocalDateTime registrationDate
    ) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                firstName != null && !firstName.isEmpty(),
                "First name can't be null or empty!");
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                secondName != null && !secondName.isEmpty(),
                "Second name can't be null or empty!");
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
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                registrationDate != null,
                 "Registration date can't be null!");

        this.id = id;
        this.email = email;
        this.isEmailConfirmed = isEmailConfirmed;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userStatus = userStatus;
        this.userRole = userRole;
        this.registrationDate = registrationDate;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPass() { return password; }

    public void setPass(String pass) { this.password = pass; }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(@NonNull String secondName) {
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

    public boolean isEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }

    @NonNull
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(@NonNull LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
