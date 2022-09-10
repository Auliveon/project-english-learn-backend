package by.savitsky.englishlearn.service;

import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.model.Word;

import java.util.List;

public interface IWordService {

    void save(Word word);

    void save(WordDto wordDto);

    void update(Word word);

    void update(WordDto wordDto);

    void save(List<Word> words);

    List<Word> getAllWords();

    List<WordDto> getAllWordDto();

    Long getWordsCount();

    List<Word> getRandomWords(int count);

    void deleteWords(List<String> pids);

    List<Word> getWordsByPids(List<String> pids);

}
