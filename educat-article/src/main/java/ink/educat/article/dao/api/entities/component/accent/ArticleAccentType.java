package ink.educat.article.dao.api.entities.component.accent;

import ink.educat.core.dao.api.entities.Jsonable;
import org.checkerframework.checker.nullness.qual.NonNull;

public enum  ArticleAccentType implements Jsonable<ArticleAccentType> {

    THEOREM("theorem"),
    ATTENTION("attention"),
    RULE("rule"),
    INFORMATION("info"),
    OTHER("other");

    private String jsonValue;

    ArticleAccentType(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public ArticleAccentType parseByJson(@NonNull final String jsonValue) {
        return Jsonable.parseByJsonValue(jsonValue, ArticleAccentType.class);
    }

    @Override
    public String getJsonValue() {
        return jsonValue;
    }
}
