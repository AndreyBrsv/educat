package ink.educat.article.dao.api.entities.metadata;

import ink.educat.core.service.api.validation.Validate;

import java.io.Serializable;

public class ArticleMetadata implements Serializable {

    @Validate(maxLength = 300)
    private String excerpt;

    private String thumbnailUrl;

    public ArticleMetadata() {
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
