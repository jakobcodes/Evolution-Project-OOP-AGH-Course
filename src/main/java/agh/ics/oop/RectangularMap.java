package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{

    private final Vector2d leftBottomCorner;
    private final Vector2d rightTopCorner;

    public RectangularMap(Integer width, Integer height) {
        this.leftBottomCorner = new Vector2d(1,1);
        this.rightTopCorner = new Vector2d(width, height);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return (isInMap(position) && super.canMoveTo(position));
    }

    @Override
    public Vector2d getLeftBottomCorner() {
        return leftBottomCorner;
    }

    @Override
    public Vector2d getRightTopCorner() {
        return rightTopCorner;
    }

    @Override
    public void placeGrass() {

    }

    @Override
    public Vector2d calculateNewPosition(Vector2d newPosition) {
        return newPosition;
    }

    public boolean isInMap(Vector2d position){
        return (position.follows(leftBottomCorner) && position.precedes(rightTopCorner));
    }

}
