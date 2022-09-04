package by.savitsky.englishlearn.training.word;

import by.savitsky.englishlearn.training.Training;

import java.util.ArrayList;
import java.util.List;

public class WordTraining implements Training {

    private Integer count;

    private List<WordUnit> wordUnits = new ArrayList<>();

    public WordTraining() {
    }

    public WordTraining(Integer count, List<WordUnit> wordUnits) {
        this.count = count;
        this.wordUnits = wordUnits;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<WordUnit> getWordUnits() {
        return wordUnits;
    }

    public void setWordUnits(List<WordUnit> wordUnits) {
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
