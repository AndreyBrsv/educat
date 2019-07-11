package ink.educat.article.dao.api.entities;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс, отражающий полноценную статью вместе с содержимым.
 *
 * @see ShortDetailedArticle
 *
 */
public class Article implements Serializable {

    /** Уникальной идентификатор статьи */
    private long id;

    /** Ссылка на главное изображение **/
    @Nullable
    private String mainImageReference;

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
    private LocalDateTime creationDate;

    public Article() {};

    public Article(final long id,
                   final long userId,
                   @NonNull final String header,
                   @NonNull final String content,
                   @Nullable final String tags,
                   @NonNull final ArticleStatus status,
                   @NonNull final LocalDateTime creationDate) {
        super();
        this.id = id;
        this.userId = userId;
        this.header = header;
        this.content = content;
        this.tags = tags;
        this.status = status;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getMainImageReference() {
        return mainImageReference;
    }

    public long getUserId() {
        return userId;
    }

    @NonNull
    public String getHeader() {
        return header;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public String getTags() {
        return tags;
    }

    @NonNull
    public ArticleStatus getStatus() {
        return status;
    }

    @NonNull
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
