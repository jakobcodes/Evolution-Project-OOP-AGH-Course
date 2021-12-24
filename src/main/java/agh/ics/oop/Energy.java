package agh.ics.oop;

public class Energy {
    private final Integer value;

    public Energy(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public Energy add(Energy other){
        return new Energy(this.value + other.getValue());
    }
    public Energy subtract(Energy other){
        if (this.value - other.getValue() < 0) return new Energy(0);
        return new Energy(this.value - other.getValue());
    }
}
