package ink.educat.article.dao.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum ArticleStatus implements Serializable {

    AVAILABLE("available", "Доступна"),
    MODERATION("moderation", "На модерации"),
    DELETED("deleted", "Удалена");

    private String jsonValue;
    private String description;

    ArticleStatus(String jsonValue, String description) {
        this.jsonValue = jsonValue;
        this.description = description;
    }

    @JsonCreator
    static ArticleStatus parseByJsonValue(final String jsonValue) {
        for (final ArticleStatus status : ArticleStatus.values()) {
            if (status.getJsonValue().equals(jsonValue)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Illegal argument " + jsonValue);
    }

    @JsonValue
    public String getJsonValue() {
        return jsonValue;
    }

    public String getDescription() {
        return description;
    }
}
