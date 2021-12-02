package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal{
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

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

    public MapDirection getOrientation() { return orientation; }

    public Vector2d getPosition() {
        return position;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        observers.forEach(e -> e.positionChanged(oldPosition,newPosition));
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
                    Vector2d old_location = this.position;
                    this.position = new_location;
                    positionChanged(old_location, new_location);
                }
            }
            case BACKWARD -> {
                Vector2d new_location = this.position.subtract(this.orientation.toUnitVector());
                if (map.canMoveTo(new_location)){
                    Vector2d old_location = this.position;
                    this.position = new_location;
                    positionChanged(old_location, new_location);
                }
            }
        }
    }
    @Override
    public String toString() {
        return orientation.toString();
    }
}
