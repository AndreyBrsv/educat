package ink.educat.dao.article.impl;

import ink.educat.dao.article.api.ArticleDao;
import ink.educat.dao.article.api.Entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ArticleDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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
    public void update(Iterable<Article> articles) {

    }

    @Override
    public void saveOrUpdate(Article article) {

    }

    @Override
    public void delete(Article article) {

    }
}
