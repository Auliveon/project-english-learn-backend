package by.savitsky.englishlearn.training.word.filter;

import by.savitsky.englishlearn.training.Filter;
import by.savitsky.englishlearn.training.IFilter;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Collections;
import java.util.List;

@Filter(value = PhraseVerbsFilter.ID)
public class PhraseVerbsFilter implements IFilter {

    public static final String ID = "phraseVerb";

    private final String id = "phraseVerb";

    private final String type = "WORD";

    private final List<String> sqlConditions = Collections.singletonList("phraseVerb = true");

    private final String description = "Выбрать только фразовые глаголы";

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public List<Criterion> getCriterionList() {
        return Collections.singletonList(Restrictions.eq("phraseVerb", true));
    }

    @Override
    public List<String> getSqlConditions() {
        return sqlConditions;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "IrregularVerbFilter{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", sqlConditions=" + sqlConditions +
                ", description='" + description + '\'' +
                '}';
    }

}
