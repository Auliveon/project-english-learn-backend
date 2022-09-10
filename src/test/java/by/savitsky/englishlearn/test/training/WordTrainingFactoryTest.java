package by.savitsky.englishlearn.test.training;

import by.savitsky.englishlearn.configuration.EnglishLearnTestConfiguration;
import by.savitsky.englishlearn.model.Word;
import by.savitsky.englishlearn.service.IWordService;
import by.savitsky.englishlearn.training.word.WordFactory;
import by.savitsky.englishlearn.training.word.WordTraining;
import by.savitsky.englishlearn.training.word.WordUnit;
import by.savitsky.englishlearn.util.Matcher;
import by.savitsky.englishlearn.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@ContextConfiguration(classes = { EnglishLearnTestConfiguration.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordTrainingFactoryTest {

    @Autowired
    private WordFactory wordFactory;

    @Autowired
    private IWordService wordService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    void setUp() {
        try (Stream<String> stream = Files.lines(
                Paths.get(ClassLoader.getSystemResource("data/source/WordsForWordTrainingTest.json").toURI()),
                StandardCharsets.UTF_8)) {
            final List<Word> words = objectMapper.readValue(
                    stream.collect(Collectors.joining()),
                    TypeFactory.defaultInstance().constructCollectionType(List.class, Word.class));
            words.forEach(wordService::save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void wordTrainingTest() {
        try (Stream<String> stream = Files.lines(
                Paths.get(ClassLoader.getSystemResource("data/expected/WordTraining.json").toURI()),
                StandardCharsets.UTF_8);
             MockedStatic<RandomUtil> randomUtil = Mockito.mockStatic(RandomUtil.class)) {
             randomUtil.when(() -> RandomUtil.getRandomIntUniqueValues(Mockito.anyInt(), Mockito.anyInt()))
                    .thenReturn(new int[] { 0, 1 });
            final WordTraining actualTraining = (WordTraining) wordFactory.create(2);
            randomUtil.verify(() -> RandomUtil.getRandomIntUniqueValues(Mockito.anyInt(), Mockito.anyInt()),
                    Mockito.times(1));
            final WordTraining expectedTraining = objectMapper.readValue(
                    stream.collect(Collectors.joining()), WordTraining.class);
            Assertions.assertTrue(new Matcher().match(expectedTraining, actualTraining,
                    Collections.singletonList(WordUnit.class)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
