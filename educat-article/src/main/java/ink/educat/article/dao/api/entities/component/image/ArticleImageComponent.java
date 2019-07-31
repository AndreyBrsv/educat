package ink.educat.article.dao.api.entities.component.image;

import ink.educat.article.dao.api.entities.component.ArticleComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Компонент, отвечающий за изображение
 */
public class ArticleImageComponent extends ArticleComponent {

    /**
     * Ссылка на изображение
     */
    @NonNull
    private String imageReference;

    /**
     * Подпись к изображению
     */
    @Nullable
    private String imageSubtitle;

    public ArticleImageComponent(@NonNull String imageReference, @Nullable String imageSubtitle) {
        this.imageReference = imageReference;
        this.imageSubtitle = imageSubtitle;
    }

    @NonNull
    public String getImageReference() {
        return imageReference;
    }

    @Nullable
    public String getImageSubtitle() {
        return imageSubtitle;
    }
}
