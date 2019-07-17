package ink.educat.user.dao.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Preconditions;
import ink.educat.core.dao.api.entities.Jsonable;
import ink.educat.user.service.api.UserService;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Перечисление, отражающее статусы доступности пользователей.
 * @author Ilya Mikheev
 */
public enum UserStatus implements Jsonable<UserStatus> {

    ACTIVE("active", "Активный аккаунт"),
    BLOCKED("blocked", "Заблокированный аккаунт"),
    DELETED("deleted", "Удаленный аккаунт");

    private String jsonValue;
    private String description;

    UserStatus(final String jsonValue, final String description) {
        this.jsonValue = jsonValue;
        this.description = description;
    }

//    @NonNull
//    public static UserStatus parseByName(@NonNull final String name) {
//        //noinspection ConstantConditions
//        Preconditions.checkArgument(
//                name != null,
//                "UserStatus name can't be empty!");
//        for (final UserStatus status : UserStatus.values()) {
//            if (status.name().equals(name)) {
//                return status;
//            }
//        }
//
//        throw new IllegalArgumentException("Incorrect UserStatus name!");
//    }

    @JsonCreator
    public static UserStatus parseByJsonValue(@NonNull final String jsonValue) {
        return Jsonable.parseByJsonValue(jsonValue, UserStatus.class);
    }


    @JsonValue
    @Override
    public String getJsonValue() { return jsonValue; }
}
