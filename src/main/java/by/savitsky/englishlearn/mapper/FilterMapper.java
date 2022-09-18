package by.savitsky.englishlearn.mapper;

import by.savitsky.englishlearn.dto.FilterDto;
import by.savitsky.englishlearn.training.IFilter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FilterMapper {

    FilterDto filterToFilterDto(IFilter filter);

    List<FilterDto> convertFilterListToFilterDtoList(List<IFilter> list);

}
