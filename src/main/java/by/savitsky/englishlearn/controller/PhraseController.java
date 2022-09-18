package by.savitsky.englishlearn.controller;

import by.savitsky.englishlearn.dto.PhraseDto;
import by.savitsky.englishlearn.service.IPhraseService;
import by.savitsky.englishlearn.training.IFilter;
import by.savitsky.englishlearn.training.TrainingFactoryProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/phrases")
public class PhraseController {

    private final IPhraseService phraseService;

    private final TrainingFactoryProvider trainingFactoryProvider;

    public PhraseController(IPhraseService phraseService, TrainingFactoryProvider trainingFactoryProvider) {
        this.phraseService = phraseService;
        this.trainingFactoryProvider = trainingFactoryProvider;
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPhrasesCount(@RequestParam(required = false, defaultValue = "") String filterId) {
        try {
            final Optional<IFilter> filterOptional = trainingFactoryProvider.getFilterById(filterId);
            final Long phrasesCount = phraseService.getPhrasesCountByCriterionList(filterOptional.map(
                    IFilter::getCriterionList).orElse(
                    Collections.emptyList()));
            return ResponseEntity.ok(phrasesCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPhrases() {
        try {
            final List<PhraseDto> phrases = phraseService.getAllPhraseDto();
            return ResponseEntity.ok(phrases);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePhrases(@RequestBody List<String> pids) {
        try {
            phraseService.deletePhrases(pids);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePhrase(@RequestBody PhraseDto phraseDto) {
        try {
            phraseService.save(phraseDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePhrase(@RequestBody PhraseDto phraseDto) {
        try {
            phraseService.update(phraseDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
