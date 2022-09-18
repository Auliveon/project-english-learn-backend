package by.savitsky.englishlearn.training.word;

import by.savitsky.englishlearn.training.Training;
import by.savitsky.englishlearn.training.Unit;

import java.util.ArrayList;
import java.util.List;

public class WordTraining implements Training {

    private Integer count;

    private List<Unit> wordUnits = new ArrayList<>();

    public WordTraining() {
    }

    public WordTraining(Integer count, List<Unit> wordUnits) {
        this.count = count;
        this.wordUnits = wordUnits;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Unit> getWordUnits() {
        return wordUnits;
    }

    public void setWordUnits(List<Unit> wordUnits) {
        this.wordUnits = wordUnits;
    }

    @Override
    public String toString() {
        return "WordTraining{" +
                "count=" + count +
                ", wordUnits=" + wordUnits +
                '}';
    }

}
