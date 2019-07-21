package ink.educat.article.dao.api.entities;

import ink.educat.article.dao.api.entities.component.ArticleComponent;
import ink.educat.article.dao.api.entities.metadata.ArticleMetadata;
import ink.educat.article.dao.api.entities.style.ArticleStyle;
import ink.educat.article.service.api.ArticleService;
import ink.educat.core.service.api.validation.Validate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс, отражающий полноценную статью вместе с содержимым.
 *
 * @see ShortDetailedArticle
 *
 */
public class Article implements Serializable {

    private long identifier;
    private long authorIdentifier;
    @Validate(maxLength = ArticleService.MAX_TITLE_LENGTH)
    private String title;
    @Validate(maxLength = ArticleService.MAX_SUBTITLE_LENGTH)
    private String subtitle;
    private boolean isBookmarked;
    private boolean isLiked;
    private long likes;
    private LocalDateTime creationDate;
    private String[] tags;
    private ArticleStyle articleStyle;
    private ArticleMetadata metadata;
    private ArticleStatus status;

    private ArticleComponent[] components;

    public Article() {}

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public long getAuthorIdentifier() {
        return authorIdentifier;
    }

    public void setAuthorIdentifier(long authorIdentifier) {
        this.authorIdentifier = authorIdentifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public ArticleStyle getArticleStyle() {
        return articleStyle;
    }

    public void setAriticleStyle(ArticleStyle articleStyle) {
        this.articleStyle = articleStyle;
    }

    public ArticleMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ArticleMetadata metadata) {
        this.metadata = metadata;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public ArticleComponent[] getComponents() {
        return components;
    }

    public void setComponents(ArticleComponent[] components) {
        this.components = components;
    }
}
