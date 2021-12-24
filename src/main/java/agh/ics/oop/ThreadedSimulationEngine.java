package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadedSimulationEngine implements IEngine, Runnable{

    private final AbstractWorldMap map;
    private final List<IPositionChangeObserver<Animal>> observers = new ArrayList<>();
    private final int moveDelay;

    public ThreadedSimulationEngine(AbstractWorldMap map) {
        this.map = map;
        this.moveDelay = 300;

        int startAnimals = Parameters.getStartAnimals();
        for (int i=0;i<startAnimals;i++){
            Vector2d pos = this.map.getFreePos();
            if(pos == null)break;
            this.map.place(new Animal(this.map, pos));
        }
    }

    @Override
    public void run() {
        List<Animal> animals = getAnimalsOnMap();
        moveAnimals(animals);
        animals.forEach(animal -> {
            List<Animal> candidates = this.map.animalsWithMaxEnergyAt(animal.getPosition());
            animal.setEnergy(EnergyCalculator.calculatePlantEnergy(animal.getEnergy(), (Integer) candidates.size()));
        });
    }
    private void moveAnimals(List<Animal> animals){
        animals.forEach(animal -> {
            Vector2d oldPosition = animal.getPosition();
            animal.move(animal.getGenome().getRandomDirection());
            Vector2d newPosition = animal.getPosition();
            positionChanged(oldPosition,newPosition,animal);
            animal.setEnergy(EnergyCalculator.calculateMoveEnergy(animal.getEnergy()));
        });
    }

    private List<Animal> getAnimalsOnMap (){
        List<Animal> animals = new LinkedList<>();
        int minX = map.getLeftBottomCorner().getX();
        int minY = map.getLeftBottomCorner().getY();
        int maxX = map.getRightTopCorner().getX();
        int maxY = map.getRightTopCorner().getY();
        for (int x = minX;x <= maxX; x++){
            for (int y = minY; y <= maxY; y++){
                Vector2d pos = new Vector2d(x,y);
                if (map.isOccupied(pos) && map.objectsAt(pos) != null){
                    animals.addAll(map.objectsAt(pos));
                }
            }
        }
        return animals;
    }

//    OBSERVER METHODS
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        observers.forEach(e -> e.positionChanged(oldPosition,newPosition,animal));
    }
}
