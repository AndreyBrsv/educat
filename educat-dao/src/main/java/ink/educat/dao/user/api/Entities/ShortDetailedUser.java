package ink.educat.dao.user.api.Entities;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;

/**
 * Эта сущность нужна для предоставления пользователю информации
 * о других пользователях.
 *
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
}
