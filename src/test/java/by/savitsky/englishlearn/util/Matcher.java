package by.savitsky.englishlearn.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Matcher {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> boolean match(T o1, T o2, List<Class<?>> classes) {
        try {
            boolean match = true;
            final Field[] classFields = o1.getClass().getDeclaredFields();
            for (Field field : classFields) {
                field.setAccessible(true);
                final Object v1 = field.get(o1);
                final Object v2 = field.get(o2);
                if (field.getType().isPrimitive()) {
                    match = v1.equals(v2);
                } else if (Objects.isNull(v1) && Objects.isNull(v2)) {
                    continue;
                } else if (Objects.isNull(v1) || Objects.isNull(v2)) {
                    match = false;
                } else if (classes.contains(field.getType())) {
                    match = match(v1, v2, classes);
                } else if (v1 instanceof Collection && v2 instanceof Collection) {
                    match = match((Collection) v1, (Collection) v2, classes);
                } else {
                    match = v1.equals(v2);
                }
                if (!match) {
                    break;
                }
            }
            return match;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public <T> boolean match(Collection<T> c1, Collection<T> c2, List<Class<?>> classes) {
        boolean match = true;
        final List<T> l1 = new ArrayList<>(c1);
        final List<T> l2 = new ArrayList<>(c2);
        for (int i = 0; i < c1.size(); i++) {
            match = match(l1.get(i), l2.get(i), classes);
            if (!match) {
                break;
            }
        }
        return match;
    }

}
