package ink.educat.article.dao.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ink.educat.core.dao.api.entities.Jsonable;
import org.checkerframework.checker.nullness.qual.NonNull;

public enum ArticleStatus implements Jsonable<ArticleStatus> {

    AVAILABLE("available", "Доступна"),
    MODERATION("moderation", "На модерации"),
    HIDDEN("hidden", "Скрыта"),
    DELETED("deleted", "Удалена");

    private String jsonValue;
    private String description;

    ArticleStatus(final String jsonValue, final String description) {
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
