package ink.educat.article.dao.api;

import ink.educat.article.dao.api.entities.ShortDetailedArticle;
import ink.educat.article.dao.api.entities.Article;
import ink.educat.core.dao.api.AbstractDao;

import java.util.List;

/**
 * Объект доступа к данным для работы со статьями.
 *
 * @see Article
 * @see ShortDetailedArticle
 *
 */
public interface ArticleDao extends AbstractDao<Article> {

    List<ShortDetailedArticle> findShortDetailedArticlesCreatedByUser(long userId);

    List<ShortDetailedArticle> findShortDetailedArticlesBookmarkedByUser(long userId);
}
