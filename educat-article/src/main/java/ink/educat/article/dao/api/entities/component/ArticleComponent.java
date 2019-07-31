package ink.educat.article.dao.api.entities.component;

import java.io.Serializable;

/**
 * Специальный класс для отображения куска содержимого статьи содержимого статьи.
 * <p>
 * Например для куска статьи, содержащей обычный текст ArticleComponentRole#PLAIN_TEXT
 * компонент можеты выглядеть так:
 * ArticleComponent {
 * role = ArticleComponentRole.PLAIN_TEXT
 * text = "SomeText"
 * }
 * Остальные поля будут пустыми.
 */
public abstract class ArticleComponent implements Serializable {

    public ArticleComponentRole role;

}
