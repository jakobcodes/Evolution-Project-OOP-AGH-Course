package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(Integer width, Integer height) {
        this.leftBottomCorner = new Vector2d(0,0);
        this.rightTopCorner = new Vector2d(width-1, height-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (isInMap(position) && super.canMoveTo(position));
    }

    public boolean isInMap(Vector2d position){
        return (position.follows(leftBottomCorner) && position.precedes(rightTopCorner));
    }
}
