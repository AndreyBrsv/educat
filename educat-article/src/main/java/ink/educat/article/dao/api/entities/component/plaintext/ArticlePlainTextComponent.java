package ink.educat.article.dao.api.entities.component.plaintext;

import com.google.common.base.Preconditions;
import ink.educat.article.dao.api.entities.component.ArticleComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Компонент, отвечающий за отображение обычного текста
 */
public class ArticlePlainTextComponent extends ArticleComponent {

    /**
     * Заголовок
     */
    @Nullable
    private String title;

    /**
     * Подзаголовок
     */
    @Nullable
    private String subtitle;

    /**
     * Содержимое
     */
    @NonNull
    private String text;

    public ArticlePlainTextComponent(@Nullable String title, @Nullable String subtitle, @NonNull String text) {

        // noinspection ConstantConditions
        Preconditions.checkArgument(
                text != null,
                "Text field content can't be null"
        );
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getSubtitle() {
        return subtitle;
    }

    @NonNull
    public String getText() {
        return text;
    }
}
