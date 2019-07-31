package ink.educat.article.dao.api.entities.component.code;

import ink.educat.core.dao.api.entities.Jsonable;

public enum ArticleProgrammingLanguage implements Jsonable<ArticleProgrammingLanguage> {

    JAVA("java"),
    SWIFT("swift");

    private String jsonValue;

    ArticleProgrammingLanguage(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public ArticleProgrammingLanguage parseByJsonValue(final String jsonValue) {
        return Jsonable.parseByJsonValue(jsonValue, ArticleProgrammingLanguage.class);
    }

    @Override
    public String getJsonValue() {
        return jsonValue;
    }
}
