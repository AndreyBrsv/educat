package ink.educat.dao.user.api.Entities;

import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Перечисление, отражающее статусы доступности пользователей.
 * @author Andrey Borisov
 */

public enum UserRole {
    ADMIN("Администратор", 1),
    MODERATOR("Модератор", 2),
    COMPANY("Компания", 3),
    TUTOR("Тьютор", 4),
    USER("Пользователь", 5);

    private String displayableName;
    private int code;

    UserRole(final String displayableName, final int code) {
        this.displayableName = displayableName;
        this.code = code;
    }

    @NonNull
    public static UserRole parseByName(@NonNull final String name) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                name != null,
                "UserRoles name can't be empty!");
        for (final UserRole role : UserRole.values()) {
            if (role.name().equals(name)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Incorrect UserRole name!");
    }

    @NonNull
    public String getDisplayableName() {
        return displayableName;
    }

    public int getCode() { return code; }
}
