package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{

    private final List<MoveDirection> moves;
    private final IWorldMap map;
    private final Vector2d[] initialPositions;

    public SimulationEngine(List<MoveDirection> moves, IWorldMap map, Vector2d[] initialPositions) {
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;

        for (Vector2d pos : initialPositions){
            map.place(new Animal(map,pos));
        }
    }

    @Override
    public void run() {
        List<Animal> animals = new ArrayList<>();
        for (Vector2d pos : initialPositions){
            if (map.objectAt(pos) instanceof Animal){
                animals.add((Animal) map.objectAt(pos));
            }
        }
        int numberOfAnimals = animals.size();
        int i = 0;
        System.out.println(map);
        for (MoveDirection move: moves){
            animals.get(i%numberOfAnimals).move(move);
            i++;
            System.out.println(map);
        }

    }
}
