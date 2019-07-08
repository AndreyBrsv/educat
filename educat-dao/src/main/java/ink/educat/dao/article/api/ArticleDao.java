package ink.educat.dao.article.api;

import ink.educat.core.api.AbstractDao;
import ink.educat.dao.article.api.Entities.Article;
import ink.educat.dao.article.api.Entities.ShortDetailedArticle;

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
