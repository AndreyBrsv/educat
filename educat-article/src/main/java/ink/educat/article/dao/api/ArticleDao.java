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

    /**
     * Позволяет получить JSON строку статьи прямо из базы.
     *
     * @param id - уникальный идентификатор
     * @return - статью в формате JSON
     * @discussion Подобный метод нужен для обеспечения высокой производительности.
     * Так как вся необходимая информация по статье хранится в jsonb формате в бд PostgreSQL,
     * то совершенно не имеет смысла проводить различного рода преобразования в виде
     * сериализации/десиреализации, а вернуть пользователю сразу JSON сроку, которую клиент сам
     * распарсит.
     */
    String getJsonArticleById(long id);

    List<ShortDetailedArticle> findShortDetailedArticlesCreatedByUser(long userId);

    List<ShortDetailedArticle> findShortDetailedArticlesBookmarkedByUser(long userId);
}
