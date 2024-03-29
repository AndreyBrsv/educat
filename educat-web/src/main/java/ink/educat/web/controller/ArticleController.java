package ink.educat.web.controller;

import ink.educat.article.service.api.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

}
