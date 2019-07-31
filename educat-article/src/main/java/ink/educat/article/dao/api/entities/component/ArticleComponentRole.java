package ink.educat.article.dao.api.entities.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ink.educat.core.dao.api.entities.Jsonable;
import org.checkerframework.checker.nullness.qual.NonNull;

public enum ArticleComponentRole implements Jsonable<ArticleComponentRole> {

    PLAIN_TEXT("plainText", "Обычный текст"),
    IMAGE("image", "Изображение"),
    IMAGE_ARRAY("images", "Массив изображений"),
    ACCENT("accent", "Акцент"),
    QUOTE("quote", "Цитата"),
    CODE("code", "Программный код"),
    FORMULA("formula", "Latex/MathJax формула");

    private String jsonValue;
    private String description;

    ArticleComponentRole(String jsonValue, String description) {
        this.jsonValue = jsonValue;
        this.description = description;
    }

    @JsonCreator
    public static ArticleComponentRole parseByJsonValue(@NonNull final String jsonValue) {
        return Jsonable.parseByJsonValue(jsonValue, ArticleComponentRole.class);
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
