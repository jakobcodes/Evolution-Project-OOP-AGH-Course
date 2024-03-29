package agh.ics.oop;

import java.util.Objects;

public class Vector2d {

    private final int x;
    private final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    Vector2d upperRight(Vector2d other) {

        int maxX = Math.max(this.x, other.x);

        int maxY = Math.max(this.y, other.y);

        return new Vector2d(maxX,maxY);
    }

    Vector2d lowerLeft(Vector2d other) {

        int minX = Math.min(this.x, other.x);

        int minY = Math.min(this.y, other.y);

        return new Vector2d(minX,minY);
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    Vector2d opposite(){
        return new Vector2d(-this.x, - this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
