package by.savitsky.englishlearn.mapper;

import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.model.Word;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WordMapper {

    WordDto wordToWordDto(Word word);

    Word wordDtoToSource(WordDto wordDto);

    List<WordDto> convertWordListToWordDtoList(List<Word> list);

    List<Word> convertWordDtoListToWordList(List<WordDto> list);

}
