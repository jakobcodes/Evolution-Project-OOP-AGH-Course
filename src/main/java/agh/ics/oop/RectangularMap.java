package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private Integer width;

    private Integer height;

    private List<Animal> animals;

    public RectangularMap(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        this.animals = new ArrayList<Animal>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInMap(position) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (isInMap(animal.getPosition()) && !isOccupied(animal.getPosition())){
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
    public Animal objectAt(Vector2d position) {
        if (isOccupied(position)){
            for (Animal animal: animals){
                if (animal.getPosition().equals(position)) return animal;
            }
        }
        return null;
    }

    public boolean isInMap(Vector2d position){
        return (position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width, height)));
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0,0), new Vector2d(width, height));
    }
}
