package by.savitsky.englishlearn.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.Collections;
import java.util.List;

public class ConvertUtils {

    public static <T> List<T> getAsList(String json, Class<T> clazz) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static <T> T getAsObject(String json, Class<T> clazz) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, TypeFactory.defaultInstance().constructType(clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
