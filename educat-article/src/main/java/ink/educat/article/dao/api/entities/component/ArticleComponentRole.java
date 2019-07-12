package ink.educat.article.dao.api.entities.component;

import java.io.Serializable;

public enum  ArticleComponentRole implements Serializable {

    PLAIN_TEXT("plainText", "Обычный текст"),
    IMAGE("image", "Изображение"),
    IMAGE_ARRAY("images","Массив изображений"),
    ACCENT("accent","Акцент"),
    QUOTE("quote", "Цитата"),
    CODE("code","Программный код");

    private String jsonName;
    private String description;

    ArticleComponentRole(String jsonName, String description) {
        this.jsonName = jsonName;
        this.description = description;
    }

    public String getJsonName() {
        return jsonName;
    }

    public String getDescription() {
        return description;
    }
}
