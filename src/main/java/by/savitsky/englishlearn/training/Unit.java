package by.savitsky.englishlearn.training;

import java.util.Objects;

public class Unit {

    private String v1;

    private String v2;

    public Unit() {
    }

    public Unit(String v1, String v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Unit))
            return false;
        Unit unit = (Unit) o;
        return v1.equals(unit.v1) && v2.equals(unit.v2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }

    @Override
    public String toString() {
        return "WordUnit{" +
                "v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                '}';
    }

}
