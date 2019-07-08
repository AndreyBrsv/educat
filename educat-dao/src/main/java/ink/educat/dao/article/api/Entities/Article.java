package ink.educat.dao.article.api.Entities;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс, отражающий полноценную статью вместе с содержимым.
 *
 * @see ShortDetailedArticle
 *
 */
public class Article implements Serializable {

    /** Уникальной идентификатор статьи */
    private long id;

    /** Уникальный идентификатор пользователя, создавшего статью */
    private long userId;

    /** Заголовок статьи*/
    @NonNull
    private String header;

    /** Содержимое статьи. Представляет из себя JSON строку */
    @NonNull
    private String content;

    /** Тэги к статье. Может использоваться для быстрого поиска */
    @Nullable
    private String tags;

    @NonNull
    private ArticleStatus status;

    /** Дата создания статьи */
    @NonNull
    private LocalDate creationDate;

    public long getId() {
        return id;
    }

    @NonNull
    public ArticleStatus getArticleStatus() {
        return status;
    }
}
