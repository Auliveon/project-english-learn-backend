package by.savitsky.englishlearn.training.word;

import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.mapper.WordMapper;
import by.savitsky.englishlearn.service.IWordService;
import by.savitsky.englishlearn.training.*;
import org.hibernate.criterion.Criterion;
import org.mapstruct.factory.Mappers;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

@TrainingFactory(value = WordFactory.FACTORY_NAME)
public class WordFactory implements IFactory {

    public static final String FACTORY_NAME = "WORD";

    private final IWordService wordService;

    private final Map<String, IFilter> filters;

    private final WordMapper mapper = Mappers.getMapper(WordMapper.class);

    public WordFactory(IWordService wordService, Map<String, IFilter> filters) {
        this.wordService = wordService;
        this.filters = filters;
    }

    @Override
    public Training create(TrainingConfig trainingConfig) {
        final Optional<IFilter> filterOptional = filters.values().stream()
                .filter(filter -> filter.getId().equals(trainingConfig.getFilterId()))
                .findFirst();
        final List<Criterion> criterionList = filterOptional.map(IFilter::getCriterionList).orElse(Collections.emptyList());
        final List<String> sqlConditions = filterOptional.map(IFilter::getSqlConditions).orElse(Collections.emptyList());
        final List<WordDto> words = mapper.convertWordListToWordDtoList(wordService.getRandomWords(criterionList, sqlConditions, trainingConfig.getCount()));
        final List<Unit> units = transform(words);
        return new WordTraining(trainingConfig.getCount(), units);

    }

    private List<Unit> transform(List<WordDto> words) {
        final List<Unit> units = new ArrayList<>();
        words.stream().map(word -> word.getTranslations().stream()
                        .map(translation -> new Unit(word.getInfinitive(), translation.getValue()))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream).distinct().forEach(units::add);

        words.stream().filter(WordDto::isIrregular)
                .map(word -> word.getSimplePastTranslations().stream()
                        .map(translation -> new Unit(word.getSimplePast(), translation.getValue()))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream).distinct().forEach(units::add);

        words.stream().filter(WordDto::isIrregular)
                .map(word -> word.getPastParticipleTranslations().stream()
                        .map(translation -> new Unit(word.getPastParticiple(), translation.getValue()))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream).distinct().forEach(units::add);
        return units;
    }

}
