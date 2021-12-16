package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class ThreadedSimulationEngine implements IEngine, Runnable{

    private List<MoveDirection> directions;
    private final AbstractWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public ThreadedSimulationEngine(List<MoveDirection> moves, AbstractWorldMap map, Vector2d[] initialPositions) {
        this.directions = moves;
        this.map = map;

        for (Vector2d pos : initialPositions){
            Animal animal = new Animal(map,pos);
            map.place(animal);
        }
    }
    public ThreadedSimulationEngine(AbstractWorldMap map, Vector2d[] initialPositions) {
        this.directions = new ArrayList<>();
        this.map = map;

        for (Vector2d pos : initialPositions){
            Animal animal = new Animal(map,pos);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        List<Animal> animals = getAnimalsOnMap();
        int numberOfAnimals = animals.size();
        int i = 0;
        System.out.println(map);
        for (MoveDirection move: directions){
            Vector2d oldPosition = animals.get(i%numberOfAnimals).getPosition();
            animals.get(i%numberOfAnimals).move(move);
            Vector2d newPosition = animals.get(i%numberOfAnimals).getPosition();
            positionChanged(oldPosition, newPosition);
            i++;
            System.out.println(map);
        }
    }
    private List<Animal> getAnimalsOnMap (){
        List<Animal> animals = new ArrayList<>();
        int minX = map.getLeftBottomCorner().x;
        int minY = map.getLeftBottomCorner().y;
        int maxX = map.getRightTopCorner().x;
        int maxY = map.getRightTopCorner().y;
        for (int x = minX;x <= maxX; x++){
            for (int y = minY; y <= maxY; y++){
                Vector2d pos = new Vector2d(x,y);
                if (map.isOccupied(pos) && map.objectAt(pos) instanceof Animal animal){
                    animals.add(animal);
                }
            }
        }
        return animals;
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }

//    OBSERVER METHODS
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        observers.forEach(e -> e.positionChanged(oldPosition,newPosition));
    }
}
