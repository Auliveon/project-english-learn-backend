package by.savitsky.englishlearn.test.mapper;

import by.savitsky.englishlearn.dto.SentenceDto;
import by.savitsky.englishlearn.dto.TranslationDto;
import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.mapper.WordMapper;
import by.savitsky.englishlearn.model.Word;
import by.savitsky.englishlearn.util.Matcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class WordMapperTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WordMapper mapper = Mappers.getMapper(WordMapper.class);

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void wordMapperTest() {
        try (Stream<String> sourceStream = Files.lines(Paths.get(ClassLoader.getSystemResource("data/source/Words.json").toURI()),
                StandardCharsets.UTF_8);
             Stream<String> expectedStream = Files.lines(Paths.get(ClassLoader.getSystemResource("data/expected/WordDtos.json").toURI()),
                     StandardCharsets.UTF_8)) {
            final List<Word> words = objectMapper.readValue(
                    sourceStream.collect(Collectors.joining()), TypeFactory.defaultInstance().constructCollectionType(List.class, Word.class));
            final List<WordDto> expected = objectMapper.readValue(
                    expectedStream.collect(Collectors.joining()), TypeFactory.defaultInstance().constructCollectionType(List.class, WordDto.class));
            final List<WordDto> actual = mapper.convertWordListToWordDtoList(words);
            assertTrue("Mather has returned false", new Matcher().match(expected, actual, Arrays.asList(
                    TranslationDto.class, SentenceDto.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
