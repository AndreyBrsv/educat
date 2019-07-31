package ink.educat.article.dao.api.entities.component.quote;

import com.google.common.base.Preconditions;
import ink.educat.article.dao.api.entities.component.ArticleComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Компонент, отвечающий за цитату
 */
public class ArticleQuoteComponent extends ArticleComponent {

    /**
     * Текст цитаты
     */
    @NonNull
    private String text;

    /**
     * Автор цитаты
     */
    @Nullable
    private String author;

    public ArticleQuoteComponent(@NonNull String text, @Nullable String author) {

        // noinspection ConstantConditions
        Preconditions.checkArgument(
                text != null,
                "Quote text can't be null"
        );
        this.text = text;
        this.author = author;

    }

    @NonNull
    public String getText() {
        return text;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }
}
