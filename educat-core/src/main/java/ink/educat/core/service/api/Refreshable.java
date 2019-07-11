package ink.educat.core.service.api;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Служебный интефейс для обновления(перезагрузки). Например
 * может использоваться для обновления кэшей или перезагрузки
 * каких-либо параметров.
 *
 */
public interface Refreshable {

    /**
     * Метод обновления(перезагрузки)
     */
    default void refresh() {
        throw new NotImplementedException();
    };
}
