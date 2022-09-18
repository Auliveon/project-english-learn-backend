package by.savitsky.englishlearn.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = Phrase.TABLE_NAME)
public class Phrase {

    public final static String TABLE_NAME = "phrases";

    public final static String TRANSLATION_TABLE_NAME = "phrase_translations";

    public final static String COLUMN_PID = "pid";

    public final static String COLUMN_VALUE = "f_value";


    public final static String FK_COLUMN_PID = "fk_phrase_pid";
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = COLUMN_PID)
    private String pid;

    @Column(name = COLUMN_VALUE, length = 2000)
    private String value;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = Phrase.TRANSLATION_TABLE_NAME, joinColumns = @JoinColumn(name = Phrase.FK_COLUMN_PID))
    @Fetch(FetchMode.SELECT)
    private Set<Translation> translations;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = Sentence.FK_COLUMN_PID, referencedColumnName = Sentence.COLUMN_PID)
    private Sentence sentence;

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

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Phrase))
            return false;
        Phrase phrase = (Phrase) o;
        return pid.equals(phrase.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "pid='" + pid + '\'' +
                ", value='" + value + '\'' +
                ", translations=" + translations +
                ", sentence=" + sentence +
                '}';
    }

}
