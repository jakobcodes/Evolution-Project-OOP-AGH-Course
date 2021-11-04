package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{

    private List<MoveDirection> moves;
    private IWorldMap map;
    private Vector2d[] initialPositions;

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
        List<Animal> animals = new ArrayList<Animal>();
        for (Vector2d pos : initialPositions){
            if (map.objectAt(pos) != null){
                animals.add((Animal) map.objectAt(pos));
            }
        }
        int numberOfAnimals = animals.size();
        int i = 0;
        for (MoveDirection move: moves){
            animals.get(i%numberOfAnimals).move(move);
            i++;
        }
    }
}
