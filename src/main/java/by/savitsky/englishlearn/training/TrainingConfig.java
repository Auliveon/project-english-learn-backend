package by.savitsky.englishlearn.training;

public class TrainingConfig {

    private String type;

    private String filterId;

    private int count;

    public TrainingConfig(String type, String filterId, int count) {
        this.type = type;
        this.filterId = filterId;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TrainingConfig{" +
                "type='" + type + '\'' +
                ", filterId='" + filterId + '\'' +
                ", count=" + count +
                '}';
    }

}
