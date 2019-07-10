package ink.educat.user.dao.api.entities;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

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
 * @see ShortDetailedUser
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = 768640870473055505L;

    /** Id пользователя */
    private long id;

    /** Пароль пользователя */
    @NonNull
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

    public User(final long id,
                @NonNull final String email,
                final boolean isEmailConfirmed,
                @NonNull final String password,
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
                password != null && !password.isEmpty(),
                "Password can't be null or empty!");
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
        this.password = password;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @Nullable
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@Nullable String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
//                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isEmailConfirmed=" + isEmailConfirmed +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", userStatus=" + userStatus +
                ", userRole=" + userRole +
                ", registrationDate=" + registrationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isEmailConfirmed == user.isEmailConfirmed &&
                Objects.equal(password, user.password) &&
                Objects.equal(email, user.email) &&
                Objects.equal(firstName, user.firstName) &&
                Objects.equal(secondName, user.secondName) &&
                Objects.equal(middleName, user.middleName) &&
                userStatus == user.userStatus &&
                userRole == user.userRole &&
                Objects.equal(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, password, email, isEmailConfirmed, firstName, secondName, middleName, userStatus, userRole, registrationDate);
    }
}
