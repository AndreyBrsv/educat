package ink.educat.core.api;

import org.springframework.lang.NonNull;

/**
 * Перечисление, описывающее стратегию удаления записи из базы данных.
 */
public enum  DeleteStrategy {

    NAIVE("Удаление из БД оператором DELETE"),
    CHANGE_STATUS("Смена статуса записи в БД на DELETED");

    private String description;

    DeleteStrategy(String description) {
        this.description = description;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

}
