package by.savitsky.englishlearn.dto;

import java.util.Objects;

public class SentenceDto {

    private String pid;

    private String value;

    private String translation;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SentenceDto))
            return false;
        SentenceDto that = (SentenceDto) o;
        return pid.equals(that.pid);
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
