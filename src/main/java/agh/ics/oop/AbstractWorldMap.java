package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals = new ArrayList<>();

    protected Vector2d leftBottomCorner;
    protected Vector2d rightTopCorner;

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal: animals){
            if (animal.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (isOccupied(position)){
            for (Animal animal: animals){
                if (animal.getPosition().equals(position)) return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(leftBottomCorner, rightTopCorner);
    }
}
