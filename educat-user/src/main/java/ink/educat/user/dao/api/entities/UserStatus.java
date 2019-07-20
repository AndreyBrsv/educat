package ink.educat.user.dao.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ink.educat.core.dao.api.entities.Jsonable;
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

    @JsonCreator
    public static UserStatus parseByJsonValue(@NonNull final String jsonValue) {
        return Jsonable.parseByJsonValue(jsonValue, UserStatus.class);
    }

    @JsonValue
    @Override
    public String getJsonValue() { return jsonValue; }

    public String getDescription() {
        return description;
    }
}
