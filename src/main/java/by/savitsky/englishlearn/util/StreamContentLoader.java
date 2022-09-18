package by.savitsky.englishlearn.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamContentLoader {

    public static String loadText(String path) {
        try (final InputStream is = StreamContentLoader.class.getResourceAsStream(path)) {
            if (Objects.nonNull(is)) {
                return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                        .lines().collect(Collectors.joining());
            }
            throw new FileNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

}
