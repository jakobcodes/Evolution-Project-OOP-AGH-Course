package agh.ics.oop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ThreadedSimulationEngine implements IEngine, Runnable{

    private final AbstractWorldMap map;
    private final List<IDayFinishedObserver> observers = new ArrayList<>();
    private final int moveDelay;

    public ThreadedSimulationEngine(AbstractWorldMap map) {
        this.map = map;
        this.moveDelay = 300;
    }

    @Override
    public void run() {
        while(true){
            this.map.deleteAnimals();
            this.map.moveAnimals();
            this.map.eatGrass();
            this.map.animalsBreed();
            this.map.placeGrass();
            this.map.incrementLifetime();
            dayFinished();
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



//    OBSERVER METHODS
    public void addObserver(IDayFinishedObserver observer){
        observers.add(observer);
    }
    public void dayFinished(){
        observers.forEach(IDayFinishedObserver::dayFinished);
    }
}
