package by.savitsky.englishlearn.controller;

import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.service.IWordService;
import by.savitsky.englishlearn.training.IFilter;
import by.savitsky.englishlearn.training.TrainingFactoryProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/words")
public class WordController {

    private final IWordService wordService;

    private final TrainingFactoryProvider trainingFactoryProvider;

    public WordController(IWordService wordService, TrainingFactoryProvider trainingFactoryProvider) {
        this.wordService = wordService;
        this.trainingFactoryProvider = trainingFactoryProvider;
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getWordsCount(@RequestParam(required = false, defaultValue = StringUtils.EMPTY) String filterId) {
        try {
            final Optional<IFilter> filterOptional = trainingFactoryProvider.getFilterById(filterId);
            final Long wordsCount = wordService.getWordsCountByCriterionList(filterOptional.map(
                    IFilter::getCriterionList).orElse(
                    Collections.emptyList()));
            return ResponseEntity.ok(wordsCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllWords() {
        try {
            final List<WordDto> words = wordService.getAllWordDto();
            return ResponseEntity.ok(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeWords(@RequestBody List<String> pids) {
        try {
            wordService.deleteWords(pids);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveWord(@RequestBody WordDto wordDto) {
        try {
            wordService.save(wordDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateWord(@RequestBody WordDto wordDto) {
        try {
            wordService.update(wordDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
