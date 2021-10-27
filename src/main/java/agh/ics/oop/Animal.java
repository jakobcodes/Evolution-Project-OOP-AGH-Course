package agh.ics.oop;

public class Animal {
    private MapDirection orientation;

    private Vector2d position;

    public Animal(MapDirection orientation, Vector2d vector2d) {
        this.orientation = orientation;
        this.position = vector2d;
    }

    public Animal(){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "orientation=" + orientation +
                ", position=" + position +
                '}';
    }

    public boolean isInside(int x, int y){
        return (x >= 0 && y >= 0 && x <= 4 && y <= 4);
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
                if (isInside(new_location.x, new_location.y)){
                    this.position = new_location;
                }
            }
            case BACKWARD -> {
                Vector2d new_location = this.position.subtract(this.orientation.toUnitVector());
                if (isInside(new_location.x, new_location.y)){
                    this.position = new_location;
                }
            }
        }
    }

}
