package by.savitsky.englishlearn.mapper;

import by.savitsky.englishlearn.dto.PhraseDto;
import by.savitsky.englishlearn.model.Phrase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PhraseMapper {

    PhraseDto phraseToPhraseDto(Phrase phrase);

    Phrase phraseDtoToPhrase(PhraseDto phraseDto);

    List<PhraseDto> convertPhraseListToPhraseDtoList(List<Phrase> list);

    List<Phrase> convertPhraseDtoListToPhraseList(List<PhraseDto> list);

}
