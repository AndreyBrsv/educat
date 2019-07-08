package ink.educat.dao.article.api.Entities;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс, содержащий себе краткую информацию о статье.
 *
 * Может использоваться, к примеру, при запросе списка
 * статей, которые пользователь добавил в закладки,
 * без учета содержимого самих статей.
 *
 * @see Article
 * @see ink.educat.dao.article.api.ArticleDao
 *
 */
public class ShortDetailedArticle implements Serializable {

    private static final long serialVersionUID = -3904310060196692164L;

    /** Уникальный идентификатор статьи */
    private long id;

    /** Имя автора статьи */
    @NonNull
    private String userName;

    /** Ссылка на главную картинку */
    @NonNull
    private String pictureReference;

    /** Заголовок статьи */
    @NonNull
    private String header;

    /** Время чтения статьи */
    @Nullable
    private String readingTime;

    /** Главный тэг статьи */
    @Nullable
    private String mainTag;

    /** Дата публикации статьи */
    @NonNull
    private LocalDate publicationDate;

    public ShortDetailedArticle(final long id,
                                @NonNull final String userName,
                                @NonNull final String pictureReference,
                                @NonNull final String header,
                                @Nullable final String readingTime,
                                @Nullable final String mainTag,
                                @NonNull final LocalDate publicationDate) {
        this.id = id;
        this.userName = userName;
        this.pictureReference = pictureReference;
        this.header = header;
        this.readingTime = readingTime;
        this.mainTag = mainTag;
        this.publicationDate = publicationDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    @NonNull
    public String getPictureReference() {
        return pictureReference;
    }

    @NonNull
    public String getHeader() {
        return header;
    }

    @Nullable
    public String getReadingTime() {
        return readingTime;
    }

    @Nullable
    public String getMainTag() {
        return mainTag;
    }

    @NonNull
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
}
