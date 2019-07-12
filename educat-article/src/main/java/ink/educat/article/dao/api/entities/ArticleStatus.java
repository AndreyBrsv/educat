package ink.educat.article.dao.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ink.educat.core.dao.entities.Jsonable;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.Serializable;

public enum ArticleStatus implements Serializable, Jsonable<ArticleStatus> {

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
    public static ArticleStatus parseByJsonValue(@NonNull final String jsonValue) {
        return Jsonable.parseByJsonValue(jsonValue, ArticleStatus.class);
    }

    @JsonValue
    @Override
    public String getJsonValue() {
        return jsonValue;
    }

    public String getDescription() {
        return description;
    }
}
