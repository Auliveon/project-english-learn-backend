package by.savitsky.englishlearn.mapper;

import by.savitsky.englishlearn.dto.TranslationDto;
import by.savitsky.englishlearn.model.Translation;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface TranslationMapper {

    TranslationDto translationToTranslationDto(Translation translation);

    Translation translationDtoToSource(TranslationDto translationDto);

    Set<TranslationDto> convertTranslationSetToTranslationDtoSet(Set<Translation> list);

    Set<Translation> convertTranslationDtoSetToTranslationList(Set<TranslationDto> list);

}
