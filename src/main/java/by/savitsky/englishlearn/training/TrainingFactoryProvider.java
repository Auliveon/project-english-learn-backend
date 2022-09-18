package by.savitsky.englishlearn.training;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TrainingFactoryProvider {

    private final Map<String, IFactory> factories;

    private final Map<String, IFilter> filters;

    public TrainingFactoryProvider(Map<String, IFactory> factories, Map<String, IFilter> filters) {
        this.factories = factories;
        this.filters = filters;
    }

    public IFactory getFactory(String type) {
        return factories.get(type);
    }

    public List<IFilter> getFilters(String type) {
        return filters.values().stream().filter(filter -> filter.getType().equals(type)).collect(Collectors.toList());
    }

    public Optional<IFilter> getFilterById(String filterId) {
        return filters.entrySet().stream().filter(entry -> entry.getKey().equals(filterId)).map(Map.Entry::getValue).findFirst();
    }



}
