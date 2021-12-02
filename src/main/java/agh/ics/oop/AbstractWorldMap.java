package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{
    protected Map<Vector2d,Animal> animals = new LinkedHashMap<>();
    protected final MapBoundary mapBoundary = new MapBoundary();;

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            animal.addObserver(mapBoundary);
            return true;
        }
        throw new IllegalArgumentException( animal.getPosition().toString() + " invalid position");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition,animals.get(oldPosition));
        animals.remove(oldPosition);
    }
    public Vector2d getLeftBottomCorner(){
        return mapBoundary.getLeftBottomCorner();
    }
    public Vector2d getRightTopCorner(){
        return mapBoundary.getRightTopCorner();
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(getLeftBottomCorner(), getRightTopCorner());
    }
}
