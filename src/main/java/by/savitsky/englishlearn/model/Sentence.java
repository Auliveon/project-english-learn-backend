package by.savitsky.englishlearn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = Sentence.TABLE_NAME)
public class Sentence {

    public final static String TABLE_NAME = "sentences";

    public final static String COLUMN_PID = "pid";

    public final static String COLUMN_VALUE = "f_value";

    public final static String COLUMN_TRANSLATION = "f_translation";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = COLUMN_PID)
    private String pid;


    @Column(name = COLUMN_VALUE, length = 2000)
    private String value;

    @Column(name = COLUMN_TRANSLATION, length = 2000)
    private String translation;

    @OneToOne
    @JoinColumn(name = Word.FK_COLUMN_PID, referencedColumnName = Word.COLUMN_PID)
    @JsonIgnore
    private Word word;

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

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Sentence))
            return false;
        Sentence sentence = (Sentence) o;
        return pid.equals(sentence.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "pid='" + pid + '\'' +
                ", value='" + value + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }

}
