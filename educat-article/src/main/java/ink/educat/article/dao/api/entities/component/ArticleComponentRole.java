package ink.educat.article.dao.api.entities.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum ArticleComponentRole implements Serializable {

    PLAIN_TEXT("plainText", "Обычный текст"),
    IMAGE("image", "Изображение"),
    IMAGE_ARRAY("images", "Массив изображений"),
    ACCENT("accent", "Акцент"),
    QUOTE("quote", "Цитата"),
    CODE("code", "Программный код");

    private String jsonValue;
    private String description;

    ArticleComponentRole(String jsonValue, String description) {
        this.jsonValue = jsonValue;
        this.description = description;
    }

    @JsonCreator
    public static ArticleComponentRole parseByJsonValue(final String jsonValue) {
        for (final ArticleComponentRole role : ArticleComponentRole.values()) {
            if (role.getJsonValue().equals(jsonValue)) {
                return role;
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
