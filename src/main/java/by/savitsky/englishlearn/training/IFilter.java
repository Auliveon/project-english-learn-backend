package by.savitsky.englishlearn.training;

import org.hibernate.criterion.Criterion;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface IFilter {

    String getId();

    String getDescription();

    String getType();

    List<Criterion> getCriterionList();

    List<String> getSqlConditions();



}
