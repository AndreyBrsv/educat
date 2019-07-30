package ink.educat.article.dao.api.entities;

import ink.educat.article.dao.api.ArticleDao;
import ink.educat.article.dao.api.entities.metadata.ArticleMetadata;
import ink.educat.article.service.api.ArticleService;
import ink.educat.core.service.api.validation.Validate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс, содержащий себе краткую информацию о статье.
 *
 * Может использоваться, к примеру, при запросе списка
 * статей, которые пользователь добавил в закладки,
 * без учета содержимого самих статей.
 *
 * @see Article
 * @see ArticleDao
 *
 */
public class ShortDetailedArticle implements Serializable {

    private static final long serialVersionUID = -5627128243963653148L;

    /**
     * Уникальный идентификатор статьи
     */
    private long id;

    /**
     * Имя автора статьи
     */
    private String author;

    /**
     * Метаинформация
     */
    private ArticleMetadata metadata;

    /**
     * Заголовок
     */
    @Validate(maxLength = ArticleService.MAX_TITLE_LENGTH)
    private String title;

    /**
     * Время чтения статьм
     */
    private String readingTime;

    /**
     * Тэги
     */
    private String[] tags;

    /**
     * Дата публикации статьи
     */
    private LocalDateTime publicationDate;

    public ShortDetailedArticle() {}

    public ShortDetailedArticle(final long id,
                                final String author,
                                final ArticleMetadata metadata,
                                final String title,
                                final String readingTime,
                                final String[] tags,
                                final LocalDateTime publicationDate) {
        this.id = id;
        this.author = author;
        this.metadata = metadata;
        this.title = title;
        this.readingTime = readingTime;
        this.tags = tags;
        this.publicationDate = publicationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArticleMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ArticleMetadata metadata) {
        this.metadata = metadata;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}