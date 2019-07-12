package ink.educat.article.dao.api.entities.metadata;

import java.io.Serializable;

public class ArticleMetadata implements Serializable {
    private String thumbnailUrl;

    public ArticleMetadata() {
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
