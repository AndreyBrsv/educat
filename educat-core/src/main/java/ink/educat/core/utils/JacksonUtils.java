package ink.educat.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Утилитный класс для конфигурации Jackson
 */
public class JacksonUtils {

    /**
     * Позволяет получить objectMapper, сконфигурированный под наши нужды, потокобезопасный.
     *
     * @return сконфигурированный objectMapper
     */
    public static ObjectMapper getObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        // Данная опция отключает сериализацию null полей
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
