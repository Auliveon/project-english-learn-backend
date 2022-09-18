package by.savitsky.englishlearn.service;

import by.savitsky.englishlearn.dto.PhraseDto;
import by.savitsky.englishlearn.model.Phrase;
import org.hibernate.criterion.Criterion;

import javax.persistence.criteria.Predicate;
import java.util.List;

public interface IPhraseService {

    void save(List<Phrase> phrases);

    void deletePhrases(List<String> pids);

    void save(Phrase phrase);

    void save(PhraseDto phraseDto);

    void update(Phrase phrase);

    void update(PhraseDto phraseDto);

    List<Phrase> getAllPhrases();

    List<Phrase> getPhrasesByPids(List<String> pids);

    List<PhraseDto> getAllPhraseDto();

    Long getPhrasesCount();

    Long getPhrasesCountByCriterionList(List<Criterion> criterionList);

    List<Phrase> getRandomPhrases(List<Criterion> criterionList, int count);

}
