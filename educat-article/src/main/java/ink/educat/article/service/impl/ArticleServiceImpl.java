package ink.educat.article.service.impl;

import ink.educat.article.dao.api.ArticleDao;
import ink.educat.article.dao.api.entities.Article;
import ink.educat.article.dao.api.entities.ShortDetailedArticle;
import ink.educat.article.service.api.ArticleService;
import ink.educat.user.dao.api.entities.User;
import ink.educat.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final UserService userService;
    private final ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(
            final UserService userService,
            final ArticleDao articleDao) {
        this.userService = userService;
        this.articleDao = articleDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Article getArticleById(final long id) {
        return articleDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShortDetailedArticle> getArticleListByTags(String... tags) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShortDetailedArticle> getInterestingArticles() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShortDetailedArticle> getInterestingArticleForUser(User user) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShortDetailedArticle> findShortDetailedArticlesBookmarkedByUser(final long userId) {
        return articleDao.findShortDetailedArticlesBookmarkedByUser(userId);
    }
}
