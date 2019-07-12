package ink.educat.core.dao.api;

import java.util.Collection;

/**
 * Абстрактный интерфейс DAO, все остальные интерфейсы DAO, требующие поддержки
 * CRUD операция наследуются от него.
 *
 * @param <Entity>
 */
public interface AbstractDao<Entity> {

    /**
     * Ищет сущность по переданному id
     *
     * @param id - id сущности
     * @return - сущность
     * сущность, так и нет
     */
    Entity findById(final long id);

    /**
     * Ищет сущности по переданным ids, не гарантирует, что количество найденных
     * сущностей будет совпадать с количеством переданных id.
     *
     * @param ids - ids сущностей
     * @return - коллекцию найденных сущностей
     */
    Collection<Entity> findByIDs(final Iterable<Long> ids);

    /**
     * Выполняет батчевое обновление сущностей, лежащих в коллекции
     *
     * @param entities - коллекция сущностей
     */
    void update(final Iterable<Entity> entities);

    /**
     * Выполняет сохранение/обновление сущности. Данным методом можно сохранять
     * Новые сущности в базу. У таких сущностей на начальном этапе поле id
     * инициализировано значенем 0. После вставки новой строки в таблицу
     * триггер возвращет значение id из базы.
     *
     * @param entity - сущность
     * @return Возвращает сохраненную или обновленную сущность
     */
    Entity saveOrUpdate(final Entity entity);

    /**
     * Выполняет удаление сущности
     *
     * @param entity - сущность
     */
    void delete(final Entity entity);

}
