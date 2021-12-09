package agh.ics.oop;

public class Grass {
    public void setPosition(Vector2d position) {
        this.position = position;
    }

    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
