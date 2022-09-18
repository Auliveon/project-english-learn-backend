package by.savitsky.englishlearn.training.phrase;

import by.savitsky.englishlearn.dto.PhraseDto;
import by.savitsky.englishlearn.mapper.PhraseMapper;
import by.savitsky.englishlearn.service.IPhraseService;
import by.savitsky.englishlearn.training.*;
import org.hibernate.criterion.Criterion;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

@TrainingFactory(value = PhraseFactory.FACTORY_NAME)
public class PhraseFactory implements IFactory {

    public static final String FACTORY_NAME = "PHRASE";

    private final IPhraseService phraseService;

    private final PhraseMapper mapper = Mappers.getMapper(PhraseMapper.class);

    private final Map<String, IFilter> filters;

    public PhraseFactory(IPhraseService phraseService, Map<String, IFilter> filters) {
        this.phraseService = phraseService;
        this.filters = filters;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Training create(TrainingConfig trainingConfig) {
        final Optional<IFilter> filterOptional = filters.values().stream()
                .filter(filter -> filter.getId().equals(trainingConfig.getFilterId()))
                .findFirst();
        final List<Criterion> criterionList = filterOptional.map(IFilter::getCriterionList).orElse(Collections.emptyList());
        final List<String> sqlConditions = filterOptional.map(IFilter::getSqlConditions).orElse(Collections.emptyList());
        final List<PhraseDto> phrases = mapper.convertPhraseListToPhraseDtoList(phraseService.getRandomPhrases(criterionList, sqlConditions, trainingConfig.getCount()));
        final List<Unit> units = transform(phrases);
        return new PhraseTraining(trainingConfig.getCount(), units);
    }

    private List<Unit> transform(List<PhraseDto> phrases) {
        return phrases.stream().map(phraseDto -> phraseDto.getTranslations().stream()
                .map(translationDto -> new Unit(phraseDto.getValue(), translationDto.getValue())).
                collect(Collectors.toList())).flatMap(Collection::stream).collect(
                Collectors.toList());
    }

}
