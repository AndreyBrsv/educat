package ink.educat.user.dao.api.entities;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;

/**
 * Эта сущность нужна для предоставления пользователю информации
 * о других пользователях.
 *
 * @see User
 */
public class ShortDetailedUser implements Serializable {

    private static final long serialVersionUID = 2273662698525464389L;

    /** Уникальный идентификатор пользователя */
    private long id;

    /** Имя пользователя. В данном случае, например, Илья Михеев */
    @NonNull
    private String userName;

    /** Краткая информация о себе, которую пользователь может заполнить */
    @Nullable
    private String userInfo;

    /** Ссылка на картинку с аватаром */
    @Nullable
    private String avatarImageReference;

    /** Количество публикаций пользователя */
    private long publications;

    /** Количество подписчиков у пользователя */
    private long subscribers;

    public ShortDetailedUser(final long id,
                             @NonNull final String userName,
                             @Nullable final String userInfo,
                             @Nullable final String avatarImageReference,
                             final long publications,
                             final long subscribers) {
        this.id = id;
        this.userName = userName;
        this.userInfo = userInfo;
        this.avatarImageReference = avatarImageReference;
        this.publications = publications;
        this.subscribers = subscribers;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    @Nullable
    public String getUserInfo() {
        return userInfo;
    }

    @Nullable
    public String getAvatarImageReference() {
        return avatarImageReference;
    }

    public long getPublications() {
        return publications;
    }

    public long getSubscribers() {
        return subscribers;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", avatarImageReference='" + avatarImageReference + '\'' +
                ", publications=" + publications +
                ", subscribers=" + subscribers +
                '}';
    }
}