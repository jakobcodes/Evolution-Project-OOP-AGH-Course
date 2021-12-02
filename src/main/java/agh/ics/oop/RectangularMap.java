package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{

    private final Vector2d leftBottomCorner;
    private final Vector2d rightTopCorner;

    public RectangularMap(Integer width, Integer height) {
        this.leftBottomCorner = new Vector2d(0,0);
        this.rightTopCorner = new Vector2d(width-1, height-1);
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

    public boolean isInMap(Vector2d position){
        return (position.follows(leftBottomCorner) && position.precedes(rightTopCorner));
    }
}
