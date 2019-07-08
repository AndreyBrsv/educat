package ink.educat.dao.user.api.Entities;

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
    private long publicationsCount;

    /** Количество подписчиков у пользователя */
    private long subscribersCount;

    public ShortDetailedUser(final long id,
                             @NonNull final String userName,
                             @Nullable final String userInfo,
                             @Nullable final String avatarImageReference,
                             final long publicationsCount,
                             final long subscribersCount) {
        this.id = id;
        this.userName = userName;
        this.userInfo = userInfo;
        this.avatarImageReference = avatarImageReference;
        this.publicationsCount = publicationsCount;
        this.subscribersCount = subscribersCount;
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

    public long getPublicationsCount() {
        return publicationsCount;
    }

    public long getSubscribersCount() {
        return subscribersCount;
    }
}