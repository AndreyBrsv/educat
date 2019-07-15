package ink.educat.web.controller;

import ink.educat.article.service.api.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController {

    /**
     * Карты запросов
     */
    private static final String POST_ARTICLE = "/article/post";
    private static final String EDIT_ARTICLE = "/article/edit";
    private static final String GET_BOOKMARKED_ARTICLES = "/article/{user_id}/bookmarked/";
    private static final String GET_INTERESTING_ARTICLES = "/article/{user_id}/interesting";
    private static final String GET_RECOMMENDED_ARTICLES = "/articles/{user_id}/recommended";
    private static final String GET_RECENTLY_READED_ARTICLES = "/articles/{user_id}/recently-read";
    private static final String GET_ARTICLES_POSTED_BY_USER = "/articles/{user-id}";


    private final ArticleService articleService;

    @Autowired
    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Методы запросов
     */


}
