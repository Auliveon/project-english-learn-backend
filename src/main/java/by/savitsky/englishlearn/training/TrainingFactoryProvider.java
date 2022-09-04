package by.savitsky.englishlearn.training;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TrainingFactoryProvider {

    private final Map<String, IFactory> factories;

    public TrainingFactoryProvider(Map<String, IFactory> factories) {
        this.factories = factories;
    }

    public IFactory getFactory(String name) {
        return factories.get(name);
    }



}
