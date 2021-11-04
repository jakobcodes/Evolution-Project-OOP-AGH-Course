package agh.ics.oop;

public class Animal {
    private MapDirection orientation;

    private Vector2d position;

    private IWorldMap map;

    public Animal(MapDirection orientation, Vector2d vector2d) {
        this.orientation = orientation;
        this.position = vector2d;
    }

    public Animal(){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection direction){
        this.orientation = direction;
        this.position = initialPosition;
        this.map = map;
    }


    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return orientation.toString();
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> {
                this.orientation = this.orientation.next();
            }
            case LEFT -> {
                this.orientation = this.orientation.previous();
            }
            case FORWARD -> {
                Vector2d new_location = this.position.add(this.orientation.toUnitVector());
                if (map.canMoveTo(new_location)){
                    this.position = new_location;
                }
            }
            case BACKWARD -> {
                Vector2d new_location = this.position.subtract(this.orientation.toUnitVector());
                if (map.canMoveTo(new_location)){
                    this.position = new_location;
                }
            }
        }
    }

}
