package ink.educat.article.dao.api.entities.component;

import ink.educat.article.service.api.ArticleService;
import ink.educat.core.service.api.validation.Validate;

import java.io.Serializable;

/**
 * Специальный класс для отображения куска содержимого статьи содержимого статьи.
 * <p>
 * Например для куска статьи, содержащей обычный текст ArticleComponentRole#PLAIN_TEXT
 * компонент можеты выглядеть так:
 * ArticleComponent {
 * role = ArticleComponentRole.PLAIN_TEXT
 * text = "SomeText"
 * }
 * Остальные поля будут пустыми.
 */
public class ArticleComponent implements Serializable {

    private static final long serialVersionUID = 5479936234295770555L;

    private ArticleComponentRole role;

    private String language;

    @Validate(maxLength = ArticleService.MAX_TITLE_LENGTH)
    private String title;

    private String text;

    private String subtext;

    private String colorSchema;

    private ArticleComponent[] images;

    private String url;

    private String imageSubtitle;

    public ArticleComponent() {
    }

    public ArticleComponentRole getRole() {
        return role;
    }

    public void setRole(ArticleComponentRole role) {
        this.role = role;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getColorSchema() {
        return colorSchema;
    }

    public void setColorSchema(String colorSchema) {
        this.colorSchema = colorSchema;
    }

    public ArticleComponent[] getImages() {
        return images;
    }

    public void setImages(ArticleComponent[] images) {
        this.images = images;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageSubtitle() {
        return imageSubtitle;
    }

    public void setImageSubtitle(String imageSubtitle) {
        this.imageSubtitle = imageSubtitle;
    }
}
