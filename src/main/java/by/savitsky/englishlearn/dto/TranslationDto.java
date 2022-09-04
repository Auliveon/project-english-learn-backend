package by.savitsky.englishlearn.dto;

public class TranslationDto {

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
