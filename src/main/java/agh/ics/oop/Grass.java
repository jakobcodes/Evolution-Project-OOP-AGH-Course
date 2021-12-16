package agh.ics.oop;

public class Grass implements IMapElement{
    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getPathToImage() {
        return "src/main/resources/grass.png";
    }

    @Override
    public String toString() {
        return "*";
    }
}
