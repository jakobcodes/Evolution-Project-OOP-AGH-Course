package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{

    private final Integer grassBorder;
    private final Map<Vector2d,Grass> grasses;

    public GrassField(Integer grassQuantity) {
        this.grasses = new HashMap<>();
        this.grassBorder = (int) Math.floor(Math.sqrt(grassQuantity*10));

        while (grassQuantity > 0){
            if (placeGrass()){
                grassQuantity--;
            }
        }
    }

    public boolean placeGrass(){
        int randomX = (int) (Math.random() * grassBorder + 1);
        int randomY = (int) (Math.random() * grassBorder + 1);
        Vector2d grassPos = new Vector2d(randomX, randomY);
        if (!isOccupied(grassPos)){
            if (!(objectAt(grassPos) instanceof Grass)){
                grasses.put(grassPos,new Grass(grassPos));
                mapBoundary.addObject(grassPos);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.canMoveTo(position)){
            return true;
        }
        return (objectAt(position) instanceof Grass);
    }

    @Override
    public boolean place(Animal animal) {
        if (super.place(animal)){
            mapBoundary.addObject(animal.getPosition());
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition().toString() + " invalid position");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        return grasses.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) return super.objectAt(position);
        return grasses.get(position);
    }
}
