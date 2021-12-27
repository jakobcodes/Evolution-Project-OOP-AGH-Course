package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Animal> deadAnimals = deleteAnimals(animals);
        animals.removeAll(deadAnimals);
        moveAnimals(animals);
        animalsEat(animals);
        animalsBreed(animals);
    }

    private void animalsBreed(List<Animal> animals) {
        int minx = this.map.getLeftBottomCorner().getX();
        int miny = this.map.getLeftBottomCorner().getY();
        int maxx = this.map.getRightTopCorner().getX();
        int maxy = this.map.getRightTopCorner().getY();
        Animal firstParent,secondParent;
        for (int x=minx;x<=maxx;x++){
            for (int y=miny;y<=maxy;y++){
                List<Animal> animalsOnPos = this.map.objectsAt(new Vector2d(x,y));
                if(animalsOnPos.size() > 2){
                    Collections.sort(animalsOnPos);
                    Collections.reverse(animalsOnPos);
                    firstParent = animalsOnPos.get(0);
                    secondParent = animalsOnPos.get(1);
                }else if (animalsOnPos.size() == 2){
                    firstParent = animalsOnPos.get(0);
                    secondParent = animalsOnPos.get(1);
                }else{
                    return;
                }
            }
        }

    }
    private Animal bornNewAnimal(Animal father, Animal mother){
        Genome childGenome;
        Energy fatherEnergy = father.getEnergy();
        Energy motherEnergy = mother.getEnergy();
        Float fatherPercent = fatherEnergy.getPercent(motherEnergy);
        Integer fatherNumberGenes = (fatherPercent * 32); // TODO: naprawic floata zeby zaokraglal do 2 miejsc
        Integer motherNumberGenes = 32 - fatherNumberGenes;
        if(fatherNumberGenes > motherNumberGenes){
            childGenome = father.getGenome().createMixedGenome(mother.getGenome(), motherNumberGenes);
        }else{
            childGenome = mother.getGenome().createMixedGenome(father.getGenome(), fatherNumberGenes);
        }
        return new Animal(this.map, father.getPosition(), energy, childGenome);// TODO : implement child energy

    }

    private void animalsEat(List<Animal> animals) {
        animals.forEach(animal -> {
            List<Animal> candidates = this.map.animalsWithMaxEnergyAt(animal.getPosition());
            animal.setEnergy(EnergyCalculator.calculatePlantEnergy(animal.getEnergy(), candidates.size()));
        });
    }

    private List<Animal> deleteAnimals(List<Animal> animals) {
        List<Animal> deadAnimals = new LinkedList<>();
        animals.forEach(animal -> {
            if (EnergyCalculator.isTooLowOnEnergy(animal.getEnergy())){
                this.map.deleteAnimal(animal);
                deadAnimals.add(animal);
            }
        });
        return deadAnimals;
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
