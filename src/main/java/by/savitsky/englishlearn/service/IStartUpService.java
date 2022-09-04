package by.savitsky.englishlearn.service;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

public interface IStartUpService {

    void saveWords();

}
