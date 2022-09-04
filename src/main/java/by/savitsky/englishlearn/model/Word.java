package by.savitsky.englishlearn.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = Word.TABLE_NAME)
public class Word {

    public final static String PAST_FULL_SUFFIX = "ed";

    public final static String FIRST_SUFFIX = "e";

    public final static String PAST_SUFFIX = "d";

    public final static String TABLE_NAME = "words";

    public final static String TRANSLATION_TABLE_NAME = "translations";

    public final static String SIMPLE_PAST_TRANSLATION_TABLE_NAME = "simple_past_translations";

    public final static String PAST_PARTICIPLE_TRANSLATION_TABLE_NAME = "past_participle_translations";

    public final static String COLUMN_PID = "pid";

    public final static String COLUMN_INFINITIVE = "f_infinitive";

    public final static String COLUMN_SIMPLE_PAST = "f_simple_past";

    public final static String COLUMN_PAST_PARTICIPLE = "f_past_participle";

    public final static String COLUMN_IS_VERB = "f_is_verb";

    public final static String COLUMN_IS_IRREGULAR = "f_is_irregular";

    public final static String FK_COLUMN_PID = "fk_word_pid";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = COLUMN_PID)
    private String pid;

    @Column(name = COLUMN_INFINITIVE, nullable = false)
    private String infinitive;

    @Column(name = COLUMN_SIMPLE_PAST)
    private String simplePast;

    @Column(name = COLUMN_PAST_PARTICIPLE)
    private String pastParticiple;

    @Column(name = COLUMN_IS_VERB, nullable = false)
    private Boolean verb;

    @Column(name = COLUMN_IS_IRREGULAR, nullable = false)
    private Boolean irregular;

    @OneToOne(mappedBy = "word", cascade = CascadeType.ALL)
    private Sentence sentence;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = Word.TRANSLATION_TABLE_NAME, joinColumns = @JoinColumn(name = Word.FK_COLUMN_PID))
    @Fetch(FetchMode.SELECT)
    private Set<Translation> translations;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = Word.SIMPLE_PAST_TRANSLATION_TABLE_NAME, joinColumns = @JoinColumn(name = Word.FK_COLUMN_PID))
    @Fetch(FetchMode.SELECT)
    private Set<Translation> simplePastTranslations;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = Word.PAST_PARTICIPLE_TRANSLATION_TABLE_NAME, joinColumns = @JoinColumn(name = Word.FK_COLUMN_PID))
    @Fetch(FetchMode.SELECT)
    private Set<Translation> pastParticipleTranslations;

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

    public Boolean getVerb() {
        return verb;
    }

    public void setVerb(Boolean verb) {
        this.verb = verb;
    }

    public Boolean getIrregular() {
        return irregular;
    }

    public void setIrregular(Boolean irregular) {
        this.irregular = irregular;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    public Set<Translation> getSimplePastTranslations() {
        return simplePastTranslations;
    }

    public void setSimplePastTranslations(Set<Translation> simplePastTranslations) {
        this.simplePastTranslations = simplePastTranslations;
    }

    public Set<Translation> getPastParticipleTranslations() {
        return pastParticipleTranslations;
    }

    public void setPastParticipleTranslations(
            Set<Translation> pastParticipleTranslations) {
        this.pastParticipleTranslations = pastParticipleTranslations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Word))
            return false;
        Word word = (Word) o;
        return pid.equals(word.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    @Override
    public String toString() {
        return "Word{" +
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
