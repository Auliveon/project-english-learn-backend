package by.savitsky.englishlearn.controller;

import by.savitsky.englishlearn.dto.FilterDto;
import by.savitsky.englishlearn.mapper.FilterMapper;
import by.savitsky.englishlearn.training.Training;
import by.savitsky.englishlearn.training.TrainingConfig;
import by.savitsky.englishlearn.training.TrainingFactoryProvider;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainingController {

    private final TrainingFactoryProvider trainingFactoryProvider;

    private final FilterMapper mapper = Mappers.getMapper(FilterMapper.class);

    public TrainingController(TrainingFactoryProvider trainingFactoryProvider) {
        this.trainingFactoryProvider = trainingFactoryProvider;
    }

    @GetMapping(value = "/training", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTraining(@RequestParam String type, @RequestParam(required = false, defaultValue = "") String filterId,
            @RequestParam Integer count) {
        try {
            final Training training = trainingFactoryProvider.getFactory(type)
                    .create(new TrainingConfig(type, filterId, count));
            return ResponseEntity.ok(training);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/filters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilters(@RequestParam String type) {
        try {
            final List<FilterDto> filters = mapper.convertFilterListToFilterDtoList(trainingFactoryProvider.getFilters(type));
            return ResponseEntity.ok(filters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
