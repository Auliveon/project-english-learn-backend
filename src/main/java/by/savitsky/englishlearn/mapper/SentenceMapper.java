package by.savitsky.englishlearn.mapper;

import by.savitsky.englishlearn.dto.SentenceDto;
import by.savitsky.englishlearn.model.Sentence;
import org.mapstruct.Mapper;

@Mapper
public interface SentenceMapper {

    SentenceDto sentenceToSentenceDto(Sentence sentence);

    Sentence sentenceDtoToSource(SentenceDto sentenceDto);

}
