package by.savitsky.englishlearn.service;

import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.model.Word;
import by.savitsky.englishlearn.training.IFilter;
import org.hibernate.criterion.Criterion;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

public interface IWordService {

    void save(Word word);

    void save(WordDto wordDto);

    void update(Word word);

    void update(WordDto wordDto);

    void save(List<Word> words);

    List<Word> getAllWords();

    List<WordDto> getAllWordDto();

    Long getWordsCount();

    Long getWordsCountByCriterionList(List<Criterion> criterionList);

    List<Word> getRandomWords(List<Criterion> criterionList, int count);

    void deleteWords(List<String> pids);

    List<Word> getWordsByPids(List<String> pids);

}
