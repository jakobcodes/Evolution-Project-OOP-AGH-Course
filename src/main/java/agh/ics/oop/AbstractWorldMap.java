package agh.ics.oop;

import java.util.*;

abstract public class AbstractWorldMap implements IWorldMap,IPositionChangeObserver<Animal>{
    protected Map<Vector2d, List<Animal>> animals = new LinkedHashMap<>();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.putIfAbsent(animal.getPosition(), new LinkedList<>());
            animals.get(animal.getPosition()).add(animal);
            Collections.sort(animals.get(animal.getPosition()));
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException( animal.getPosition().toString() + " invalid position");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.get(position) != null && animals.get(position).size() != 0;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.get(position).size() == 0 || !isOccupied(position)) {
            animals.remove(position);
            return null;
        }
        return animals.get(position).get(animals.get(position).size()-1);
    }
    public List<Animal> animalsWithMaxEnergyAt(Vector2d position){
        Animal firstAnimal = (Animal) objectAt(position);
        Integer maxEnergy = firstAnimal.getEnergy().getValue();
        return animals.get(position).stream().filter(e -> e.getEnergy().getValue().equals(maxEnergy)
        ).toList();
    }
    public List<Animal> objectsAt(Vector2d position ){
        return animals.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        animals.putIfAbsent(newPosition, new LinkedList<>());
        animals.get(newPosition).add(animal);
        Collections.sort(animals.get(newPosition));
        removeAnimalFromMap(oldPosition,animal);
    }
    protected void removeAnimalFromMap(Vector2d position, Animal animal){
        animals.get(position).remove(animal);
        if (animals.get(position).size() == 0) animals.remove(position);
    }

    public abstract Vector2d getLeftBottomCorner();
    public abstract Vector2d getRightTopCorner();
    public abstract void placeGrass();
    public abstract Vector2d calculateNewPosition(Vector2d newPosition);
    public abstract Vector2d getFreePos();



    @Override
    public String toString() {
        return new MapVisualizer(this).draw(getLeftBottomCorner(), getRightTopCorner());
    }


}
