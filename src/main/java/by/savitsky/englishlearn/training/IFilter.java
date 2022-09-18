package by.savitsky.englishlearn.training;

import org.hibernate.criterion.Criterion;

import java.util.List;

public interface IFilter {

    String getId();

    String getDescription();

    String getType();

    List<Criterion> getCriterionList();



}
