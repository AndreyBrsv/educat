package ink.educat.article.dao.api.entities.component.accent;

import com.google.common.base.Preconditions;
import ink.educat.article.dao.api.entities.component.ArticleComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Компонент, отвечающий за блок с акцентом.
 * В блоке акцента можно записать, например какую-нибудь теорему
 * <p>
 * |   ТЕОРЕМА
 * |   Cумма квадратов катетов равна
 * |   квадрату гиппотенузы.
 * <p>
 * При этом на осноавании типа ArticleAccentType на клиенте выбираетсяё
 * цветовая схема.
 */
public class ArticleAccentComponent extends ArticleComponent {

    /**
     * Заголовк
     */
    @Nullable
    private String title;

    /**
     * Содержимое
     */
    @NonNull
    private String text;

    /**
     * Тип акцента
     */
    @NonNull
    private ArticleAccentType accentType;

    public ArticleAccentComponent(@Nullable String title, @NonNull String text, @NonNull ArticleAccentType accentType) {

        // noinspection ConstantConditions
        Preconditions.checkArgument(
                text != null && !text.isBlank(),
                "Text field should content something!"
        );
        // noinspection ConstantConditions
        Preconditions.checkArgument(
                accentType != null,
                "Article accent type can't be"
        );
        this.title = title;
        this.text = text;
        this.accentType = accentType;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @NonNull
    public ArticleAccentType getAccentType() {
        return accentType;
    }
}
