package by.savitsky.englishlearn.training.word;

import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.mapper.WordMapper;
import by.savitsky.englishlearn.service.IWordService;
import by.savitsky.englishlearn.training.IFactory;
import by.savitsky.englishlearn.training.Training;
import by.savitsky.englishlearn.training.TrainingFactory;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@TrainingFactory(value = WordFactory.FACTORY_NAME)
public class WordFactory implements IFactory {

    public static final String FACTORY_NAME = "WORD";

    private final IWordService wordService;

    private final WordMapper mapper = Mappers.getMapper(WordMapper.class);

    public WordFactory(IWordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public Training create(int count) {
        final List<WordDto> words = mapper.convertWordListToWordDtoList(wordService.getRandomWords(count));
        final List<WordUnit> units = new ArrayList<>();
        words.stream().map(word -> word.getTranslations().stream()
                        .map(translation -> new WordUnit(word.getInfinitive(), translation.getValue()))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream).distinct().forEach(units::add);

        words.stream().map(word -> word.getSimplePastTranslations().stream()
                        .map(translation -> new WordUnit(word.getSimplePast(), translation.getValue()))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream).distinct().forEach(units::add);

        words.stream().map(word -> word.getPastParticipleTranslations().stream()
                        .map(translation -> new WordUnit(word.getPastParticiple(), translation.getValue()))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream).distinct().forEach(units::add);
        return new WordTraining(count, units);

    }

}
