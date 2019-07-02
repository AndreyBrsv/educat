package ink.educat.user.api.Entities;

import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Перечисление, отражающее статусы доступности пользователей.
 * @author Andrey Borisov
 */

public enum UserRole {
    ADMIN("Администратор"),
    MODERATOR("Модератор"),
    COMPANY("Компания"),
    TUTOR("Тьютор"),
    USER("Пользователь");

    private String displayableName;

    UserRole(final String displayableName) {
        this.displayableName = displayableName;
    }

    @NonNull
    public UserRole parseByName(@NonNull final String name) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                name != null,
                "UserRoles name can't be empty!");
        for (final UserRole role : UserRole.values()) {
            if (role.name().equals(name)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Incorrect UserStatus name!");
    }

    @NonNull
    public String getDisplayableName() {
        return displayableName;
    }
}
