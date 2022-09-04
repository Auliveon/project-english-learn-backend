package by.savitsky.englishlearn;

import by.savitsky.englishlearn.configuration.EnglishLearnTestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = { EnglishLearnTestConfiguration.class })
class EnglishLearnApplicationTests {

}
