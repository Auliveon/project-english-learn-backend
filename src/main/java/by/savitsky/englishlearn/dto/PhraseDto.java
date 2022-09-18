package by.savitsky.englishlearn.dto;

import java.util.Set;

public class PhraseDto {

    private String pid;

    private String value;

    private Set<TranslationDto> translations;

    private SentenceDto sentence;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<TranslationDto> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<TranslationDto> translations) {
        this.translations = translations;
    }

    public SentenceDto getSentence() {
        return sentence;
    }

    public void setSentence(SentenceDto sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "PhraseDto{" +
                "pid='" + pid + '\'' +
                ", value='" + value + '\'' +
                ", translations=" + translations +
                ", sentence=" + sentence +
                '}';
    }

}
