package ink.educat.article.dao.api.entities.component.image;

/**
 * Компонент, отвечающий за отображение баннера с несколькими картинками
 */
public class ArticleImageArrayComponent {

    private ArticleImageComponent[] images;

    public ArticleImageArrayComponent(ArticleImageComponent[] images) {
        this.images = images;
    }

    public ArticleImageComponent[] getImages() {
        return images;
    }
}
