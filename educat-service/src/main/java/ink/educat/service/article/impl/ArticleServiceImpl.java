package ink.educat.service.article.impl;

import com.google.common.base.Preconditions;
import ink.educat.dao.article.api.ArticleDao;
import ink.educat.dao.article.api.Entities.Article;
import ink.educat.dao.article.api.Entities.ShortDetailedArticle;
import ink.educat.dao.user.api.Entities.User;
import ink.educat.service.article.api.ArticleService;
import ink.educat.service.user.api.UserService;
import org.checkerframework.checker.nullness.qual.NonNull;
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
    public List<ShortDetailedArticle> findShortDetailedArticlesBookmarkedByUser(@NonNull final User user) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                user != null,
                "User can't be null!"
        );
        return articleDao.findShortDetailedArticlesBookmarkedByUser(user.getId());
    }
}
