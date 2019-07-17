package ink.educat.user.dao.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;
import ink.educat.core.dao.api.entities.Jsonable;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Перечисление, отражающее статусы доступности пользователей.
 * @author Andrey Borisov
 */

public enum UserRole implements Jsonable<UserRole> {
    ADMIN("administrator", 1),
    MODERATOR("moderator", 2),
    COMPANY("company", 3),
    TUTOR("tutor", 4),
    USER("user", 5);

    private String jsonValue;
    private int code;

    UserRole(final String jsonValue, final int code) {
        this.jsonValue = jsonValue;
        this.code = code;
    }

    //todo уже не нужно, так как есть parseByJsonValue? Аналогично и в UserStatus
    @NonNull
//    public static UserRole parseByName(@NonNull final String name) {
//        //noinspection ConstantConditions
//        Preconditions.checkArgument(
//                name != null,
//                "UserRoles name can't be empty!");
//        for (final UserRole role : UserRole.values()) {
//            if (role.name().equals(name)) {
//                return role;
//            }
//        }
//
//        throw new IllegalArgumentException("Incorrect UserRole name!");
//    }

    @JsonCreator
    public static UserRole parseByJsonValue(@NonNull final String jsonValue) {
        return Jsonable.parseByJsonValue(jsonValue, UserRole.class);
    }

    @JsonValue
    @Override
    public String getJsonValue() { return jsonValue; }

    public int getCode() { return code; }
}
