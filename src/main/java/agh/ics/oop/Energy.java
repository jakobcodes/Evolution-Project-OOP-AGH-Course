package agh.ics.oop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Energy implements Comparable<Energy>{
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
    public Float getPercent(Energy other){
        BigDecimal bg1 = new BigDecimal(this.getValue());
        BigDecimal bg2 = new BigDecimal(this.getValue() + other.getValue());
        return bg1.divide(bg2, 2, RoundingMode.FLOOR).floatValue();
    }

    public Energy addQuarter(Energy other){
        int otherValue = (int) Math.round(other.getValue() * 0.25);
        return this.add(new Energy(otherValue));
    }
    @Override
    public int compareTo(Energy e) {
        return Integer.compare(this.value, e.value);
    }

    public boolean isDead(){
        return value == 0;
    }
}
