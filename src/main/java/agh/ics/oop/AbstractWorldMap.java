package agh.ics.oop;

import java.util.*;

abstract public class AbstractWorldMap implements IWorldMap,IPositionChangeObserver<Animal>{
    protected Map<Vector2d, List<Animal>> animals = new LinkedHashMap<>();
    protected List<Animal> deadAnimals = new LinkedList<>();

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }


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

    public boolean isOccupied(Vector2d position) {
        if (animals.get(position) == null) return false;
        return animals.get(position).size() != 0;
    }

    public Object objectAt(Vector2d position) {
        if (animals.get(position) != null && animals.get(position).size() > 0){
            return animals.get(position).get(animals.get(position).size()-1);
        }else{
            return null;
        }

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

    public void deleteAnimal(Animal animal){
        deadAnimals.add(animal);
        objectsAt(animal.getPosition()).remove(animal);
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
    }

    public abstract Vector2d getLeftBottomCorner();
    public abstract Vector2d getRightTopCorner();
    public abstract void placeGrass();
    public abstract Vector2d calculateNewPosition(Vector2d newPosition);
    public abstract Vector2d getFreePos();
    protected abstract List<Animal> getAnimalsOnMap();
    public abstract void deleteAnimals();
    public abstract void animalsBreed();
    public abstract void moveAnimals();
    public abstract void eatGrass();
    public abstract int countAnimals();
    public abstract int countGrasses();
    public abstract int countAvgEnergy();
    public abstract int countAvgLifetime();
    public abstract void incrementLifetime();
    public abstract int countAvgChildren();



    @Override
    public String toString() {
        return new MapVisualizer(this).draw(getLeftBottomCorner(), getRightTopCorner());
    }


}
