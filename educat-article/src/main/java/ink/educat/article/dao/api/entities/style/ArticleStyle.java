package ink.educat.article.dao.api.entities.style;

import java.io.Serializable;

public class ArticleStyle implements Serializable {

    private String backgroundColor;

    public ArticleStyle() {
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
