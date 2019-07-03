package ink.educat.article.impl;

import ink.educat.article.api.ArticleDao;
import ink.educat.article.api.Entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article findById(long id) {
        return null;
    }

    @Override
    public Collection<Article> findByIDs(Iterable<Long> ids) {
        return null;
    }

    @Override
    public void saveOrUpdate(Iterable<Article> articles) {

    }

    @Override
    public void saveOrUpdate(Article article) {

    }

    @Override
    public void delete(Article article) {

    }
}
