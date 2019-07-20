package ink.educat.core.dao.api;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;

/**
 * Абстрактный интерфейс DAO, все остальные интерфейсы DAO, требующие поддержки
 * CRUD операция наследуются от него.
 *
 * @param <Entity>
 */
public interface AbstractDao<Entity> {

    /**
     * Ищет сущность по переданному id. При отсутствии сущности, должен выбрасывать
     * спецефичное исключени, какое именно, описывается в документации к конкретной
     * реализации.
     *
     * @param id - id сущности
     * @return - сущность
     */
    @NonNull
    Entity findById(final long id);

    /**
     * Ищет сущности по переданным ids, не гарантирует, что количество найденных
     * сущностей будет совпадать с количеством переданных id. Всегда возвращает
     * коллекцию, но она может быть пустой.
     *
     * @param ids - ids сущностей
     * @return - коллекцию найденных сущностей
     */
    Collection<Entity> findByIDs(@NonNull final Iterable<Long> ids);

    /**
     * Выполняет батчевое обновление сущностей, лежащих в коллекции
     *
     * @param entities - коллекция сущностей
     */
    void update(@NonNull final Iterable<Entity> entities);

    /**
     * Выполняет сохранение/обновление сущности. Данным методом можно сохранять
     * Новые сущности в базу. У таких сущностей на начальном этапе поле id
     * инициализировано значенем 0. После вставки новой строки в таблицу
     * триггер возвращет значение id из базы.
     *
     * @param entity - сущность
     * @return Возвращает сохраненную или обновленную сущность
     */
    Entity saveOrUpdate(@NonNull final Entity entity);

    /**
     * Выполняет удаление сущности.
     *
     * @param entity - сущность
     */
    void delete(@NonNull final Entity entity);

}
