package agh.ics.oop;

public class Gene implements Comparable<Gene>{
    private final Integer value;

    public Gene(int gene) {
        if (gene < 0 || gene > 7) throw new IllegalArgumentException();
        this.value = gene;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public int getValue() {
        return value;
    }


    @Override
    public int compareTo(Gene o) {
        return Integer.compare(this.getValue(), o.getValue());
    }
}
