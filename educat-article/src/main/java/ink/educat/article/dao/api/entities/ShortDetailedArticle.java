package ink.educat.article.dao.api.entities;

import ink.educat.article.dao.api.ArticleDao;
import ink.educat.article.dao.api.entities.metadata.ArticleMetadata;

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

    private long id;
    private String author;
    private ArticleMetadata metadata;
    private String title;
    private String readingTime;
    private String[] tags;
    private LocalDateTime publicationDate;

}