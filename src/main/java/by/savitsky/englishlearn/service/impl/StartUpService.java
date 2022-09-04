package by.savitsky.englishlearn.service.impl;

import by.savitsky.englishlearn.model.Word;
import by.savitsky.englishlearn.service.IStartUpService;
import by.savitsky.englishlearn.service.IWordService;
import by.savitsky.englishlearn.util.ConvertUtils;
import by.savitsky.englishlearn.util.StreamContentLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartUpService implements IStartUpService {

    private final IWordService wordService;

    private final boolean enable;

    public StartUpService(@Value("${STARTUP_SERVICE_ENABLE:false}") boolean enable, IWordService wordService) {
        this.wordService = wordService;
        this.enable = enable;
    }

    @Override
    @EventListener(ApplicationStartedEvent.class)
    public void saveWords() {
        if (enable) {
            final List<Word> words = ConvertUtils.getAsList(StreamContentLoader
                    .loadText("/static/data/words.json"), Word.class);
            words.forEach(wordService::save);
        }
    }

}

