package ink.educat.article.dao.api.entities.style;

import java.io.Serializable;

public class ArticleStyle implements Serializable {

    private static final long serialVersionUID = -3523030128340904322L;
    
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
