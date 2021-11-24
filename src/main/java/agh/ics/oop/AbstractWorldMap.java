package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

abstract public class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{
//    protected List<Animal> animals = new ArrayList<>();
    protected Map<Vector2d,Animal> animals = new LinkedHashMap<>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
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
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(10,10));
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition,animals.get(oldPosition));
        animals.remove(oldPosition);
    }
}
