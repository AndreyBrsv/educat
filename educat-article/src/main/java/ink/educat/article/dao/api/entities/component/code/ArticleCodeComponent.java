package ink.educat.article.dao.api.entities.component.code;

import com.google.common.base.Preconditions;
import ink.educat.article.dao.api.entities.component.ArticleComponent;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Компонент, отвечающий за отображение кода
 */
public class ArticleCodeComponent extends ArticleComponent {

    /**
     * Тип программного языка
     */
    @NonNull
    private ArticleProgrammingLanguage language;

    /**
     * Содержимое
     */
    @NonNull
    private String text;

    public ArticleCodeComponent(@NonNull ArticleProgrammingLanguage language, @NonNull String text) {

        // noinspection ConstantConditions
        Preconditions.checkArgument(
                text != null && !text.isBlank(),
                "Text field of code component can't be null or empty!"
        );
        // noinspection ConstantConditions
        Preconditions.checkArgument(
                language != null,
                "Language type of code component can't be null"
        );

        this.language = language;
        this.text = text;
    }

    @NonNull
    public ArticleProgrammingLanguage getLanguage() {
        return language;
    }

    @NonNull
    public String getText() {
        return text;
    }
}
