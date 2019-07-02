package ink.educat.user.api.Entities;

import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Перечисление, отражающее статусы доступности пользователей.
 * @author Ilya Mikheev
 */
public enum  UserStatus {

    AVAILABLE("Доступен"),
    BLOCKED("Заблокирован"),
    DELETED("Удален");

    private String displayableName;

    UserStatus(final String displayableName) {
        this.displayableName = displayableName;
    }

    @NonNull
    public static UserStatus parseByName(@NonNull final String name) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                name != null,
                "UserStatus name can't be empty!");
        for (final UserStatus status : UserStatus.values()) {
            if (status.name().equals(name)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Incorrect UserStatus name!");
    }

    @NonNull
    public String getDisplayableName() {
        return displayableName;
    }
}
