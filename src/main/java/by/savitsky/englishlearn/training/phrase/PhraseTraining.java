package by.savitsky.englishlearn.training.phrase;

import by.savitsky.englishlearn.training.Training;
import by.savitsky.englishlearn.training.Unit;

import java.util.ArrayList;
import java.util.List;

public class PhraseTraining implements Training {
    private Integer count;

    private List<Unit> phraseUnits = new ArrayList<>();

    public PhraseTraining() {
    }

    public PhraseTraining(Integer count, List<Unit> phraseUnits) {
        this.count = count;
        this.phraseUnits = phraseUnits;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Unit> getPhraseUnits() {
        return phraseUnits;
    }

    public void setPhraseUnits(List<Unit> phraseUnits) {
        this.phraseUnits = phraseUnits;
    }

    @Override
    public String toString() {
        return "PhraseTraining{" +
                "count=" + count +
                ", phraseUnits=" + phraseUnits +
                '}';
    }

}
