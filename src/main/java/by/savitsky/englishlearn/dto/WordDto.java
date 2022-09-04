package by.savitsky.englishlearn.dto;

import java.util.Objects;
import java.util.Set;

public class WordDto {

    private String pid;

    private String infinitive;

    private String simplePast;

    private String pastParticiple;

    private boolean verb;

    private boolean irregular;

    private SentenceDto sentence;

    private Set<TranslationDto> translations;

    private Set<TranslationDto> simplePastTranslations;

    private Set<TranslationDto> pastParticipleTranslations;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getInfinitive() {
        return infinitive;
    }

    public void setInfinitive(String infinitive) {
        this.infinitive = infinitive;
    }

    public String getSimplePast() {
        return simplePast;
    }

    public void setSimplePast(String simplePast) {
        this.simplePast = simplePast;
    }

    public String getPastParticiple() {
        return pastParticiple;
    }

    public void setPastParticiple(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    public boolean isVerb() {
        return verb;
    }

    public void setVerb(boolean verb) {
        this.verb = verb;
    }

    public boolean isIrregular() {
        return irregular;
    }

    public void setIrregular(boolean irregular) {
        this.irregular = irregular;
    }

    public SentenceDto getSentence() {
        return sentence;
    }

    public void setSentence(SentenceDto sentence) {
        this.sentence = sentence;
    }

    public Set<TranslationDto> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<TranslationDto> translations) {
        this.translations = translations;
    }

    public Set<TranslationDto> getSimplePastTranslations() {
        return simplePastTranslations;
    }

    public void setSimplePastTranslations(Set<TranslationDto> simplePastTranslations) {
        this.simplePastTranslations = simplePastTranslations;
    }

    public Set<TranslationDto> getPastParticipleTranslations() {
        return pastParticipleTranslations;
    }

    public void setPastParticipleTranslations(
            Set<TranslationDto> pastParticipleTranslations) {
        this.pastParticipleTranslations = pastParticipleTranslations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof WordDto))
            return false;
        WordDto wordDto = (WordDto) o;
        return pid.equals(wordDto.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    @Override
    public String toString() {
        return "WordDto{" +
                "pid='" + pid + '\'' +
                ", infinitive='" + infinitive + '\'' +
                ", simplePast='" + simplePast + '\'' +
                ", pastParticiple='" + pastParticiple + '\'' +
                ", verb=" + verb +
                ", irregular=" + irregular +
                ", sentence=" + sentence +
                ", translations=" + translations +
                ", simplePastTranslations=" + simplePastTranslations +
                ", pastParticipleTranslations=" + pastParticipleTranslations +
                '}';
    }

}
