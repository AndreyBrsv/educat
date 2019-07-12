package ink.educat.article.dao.api.entities;

import java.io.Serializable;

public enum ArticleStatus implements Serializable {

    AVAILABLE("available", "Доступна"),
    MODERATION("moderation", "На модерации"),
    DELETED("deleted", "Удалена");

    private String jsonName;
    private String description;

    ArticleStatus(String jsonName, String description) {
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
