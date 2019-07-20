package ink.educat.article.dao.impl;

import com.google.common.base.Preconditions;
import ink.educat.article.dao.api.ArticleDao;
import ink.educat.article.dao.api.entities.Article;
import ink.educat.article.dao.api.entities.ShortDetailedArticle;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    // Поля для @Autowired, не забывать добавлять в конструктор
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ArticleDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Мапперы из БД в Java
     */
    private final RowMapper<ShortDetailedArticle> shortDetailedArticleRowMapper =
            ((resultSet, i) -> new ShortDetailedArticle(

            ));

    private final RowMapper<Article> articleRowMapper =
            ((resultSet, i) -> new Article(

            ));

    /**
     * Мапперы из Java в БД
     */


    @NonNull
    @Override
    public Article findById(long id) {
        Preconditions.checkArgument(
                id > 0,
                "Article id can't be less than 0!"
        );

        final MapSqlParameterSource  mapSqlParameterSource =
                new MapSqlParameterSource().addValue("id", id);

        List<Article> articles = namedParameterJdbcTemplate.query(
                "SELECT * FROM EC_ARTICLES A\n" +
                        "JOIN EC_USERS U ON A.USER_ID = U.USER_ID\n" +
                        "WHERE ARTICLE_ID = :article_id;",
                mapSqlParameterSource,
                articleRowMapper
        );

        Preconditions.checkState(
                !articles.isEmpty(),
                "No article found with given email!"
        );

        Preconditions.checkState(
                articles.size() == 1,
                "Found multiple articles with this id!"
        );

        return articles.iterator().next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Article> findByIDs(@NonNull Iterable<Long> ids) {
        final Iterator iterator = ids.iterator();
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(@NonNull Iterable<Article> articles) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Article saveOrUpdate(@NonNull Article article) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(@NonNull Article article) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                article != null,
                "Article can't be null!");

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("id", article.getIdentifier());

        namedParameterJdbcTemplate.queryForMap(
                "DELETE FROM EC_ARTICLES WHERE ARTICLE_ID = :id",
                mapSqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJsonArticleById(long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShortDetailedArticle> findShortDetailedArticlesCreatedByUser(final long userId) {
        Preconditions.checkArgument(
                userId > 0,
                "User id can't be less than 0!"
        );

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("user_id", userId);

        return namedParameterJdbcTemplate.query(
                "SELECT A.ARTICLE_ID article_id, \n" +
                        "CONCAT(U.FIRST_NAME, ' ', U.SECOND_NAME) user_name, \n" +
                        "A.PICTURE_REFERENCE picture_reference, \n" +
                        "A.HEADER header, \n" +
                        "A.READING_TIME reading_time, \n" +
                        "A.PUBLICATION_DATE publication_date \n" +
                        "FROM EC_ARTICLES A\n" +
                        "JOIN EC_USERS U \n" +
                        "   ON U.USER_ID = A.USER_ID \n" +
                        "WHERE U.USER_ID = :user_id",
                mapSqlParameterSource,
                shortDetailedArticleRowMapper
        );
    }

    /**
     * {@inheritDoc}
     *
     * @discussion Не бросаем никаких исключений, так как список закладок пользователя
     * может быть пустым и это не ошибка и не исключительная ситуация.
     */
    @Override
    public List<ShortDetailedArticle> findShortDetailedArticlesBookmarkedByUser(final long userId) {

        Preconditions.checkArgument(
                userId > 0,
                "User id can't be less than 0!"
        );

        final MapSqlParameterSource mapSqlParameterSource =
                new MapSqlParameterSource().addValue("user_id", userId);

        return namedParameterJdbcTemplate.query(
                "SELECT A.ARTICLE_ID article_id, \n" +
                        "CONCAT(U.FIRST_NAME, ' ', U.SECOND_NAME) user_name, \n" +
                        "A.PICTURE_REFERENCE picture_reference, \n" +
                        "A.HEADER header, \n" +
                        "A.READING_TIME reading_time, \n" +
                        "A.PUBLICATION_DATE publication_date \n" +
                        "FROM EC_ARTICLES A\n" +
                        "JOIN EC_USER_BOOKMARKED_ARTICLES UBA \n" +
                        "   ON A.ARTICLE_ID = UBA.ARTICLE_ID\n" +
                        "JOIN EC_USERS U \n" +
                        "   ON U.USER_ID = UBA.USER_ID \n" +
                        "WHERE U.USER_ID = :user_id",
                mapSqlParameterSource,
                shortDetailedArticleRowMapper
        );

    }

}
