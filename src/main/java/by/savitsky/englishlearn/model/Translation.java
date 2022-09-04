package by.savitsky.englishlearn.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Translation {

    public static final String COLUMN_VALUE = "f_value";

    @Column(name = COLUMN_VALUE, nullable = false)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "value='" + value + '\'' +
                '}';
    }

}
