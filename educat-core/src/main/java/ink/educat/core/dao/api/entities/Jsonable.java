package ink.educat.core.dao.api.entities;

import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Специальный интерфейс, который должны наследовать перечисления,
 * сериализуемые в Json формат.
 *
 * Для того, чтобы сделать любой enum Jsonable:
 * 1. Нужно реализовать метод getJsonValue();
 * 2. Реализовать метод parseByJsonValue(String jsonValue) следующим образом
 * static ТипПеречисления parseByJsonValue(String jsonValue) {
 *     return Jsonable.parseByJsonValue(jsonValue, ТипПеречисления.class);
 * }
 * 3. Аннотировать getJsonValue() аннотацией @JsonValue
 * 4. Аннотировать parseByJsonValue(String jsonValue) аннотацией @JsonCreator
 * getJsonValue() должен возвращать поле, связанное с той или иной константой
 * перечисления.
 *
 * @param <T> - Тип перечисления
 *
 * @discussion Не известно будет ли стоит много такой оверхед из
 * рефлексии при большой нагрузке. Поживем-увидим. Устал писать
 * одинаковый код в перечислениях, которые должны сериализоваться
 * в JSON, решил придумать такой микрохак.
 */
public interface Jsonable<T extends Enum<T>> extends Serializable {

    String getJsonValue();

    static <T extends Enum<T>> T parseByJsonValue(@NonNull final String jsonValue, Class<T> type) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                jsonValue != null && !jsonValue.isEmpty(),
                "JsonValue can't be null!"
        );
        try {
            for (final T item : type.getEnumConstants()) {
                final Method getJsonValue = item.getClass().getMethod("getJsonValue");
                if (((String) getJsonValue.invoke(item)).equals(jsonValue)) {
                    return item;
                }
            }
        } catch (final Exception ex) {
            // Сделаем перехват исключений тут
            ex.printStackTrace();
        }
        throw new IllegalArgumentException("Illegal argument " + jsonValue);
    }

}
