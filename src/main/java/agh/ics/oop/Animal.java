package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement, Comparable<Animal>{
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    private Energy energy;
    private final Genome genome;

    public Animal(AbstractWorldMap map, Vector2d initialPosition){
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
        this.energy = new Energy(Parameters.getStartEnergy());
        this.genome = new Genome();
    }
    public Animal(AbstractWorldMap map, Vector2d initialPosiiton, Genome genome, Energy energy){
        this.orientation = MapDirection.NORTH;
        this.position = initialPosiiton;
        this.map = map;
        this.energy = energy;
        this.genome = genome;
    }

    public MapDirection getOrientation() { return orientation; }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getPathToImage() {
        return switch(this.orientation) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
            case EAST -> "src/main/resources/right.png";
            case NORTH_EAST -> "src/main/resources/up.png";
            case NORTH_WEST -> "src/main/resources/up.png";
            case SOUTH_EAST -> "src/main/resources/down.png";
            case SOUTH_WEST -> "src/main/resources/down.png";
        };
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        observers.forEach(e -> e.positionChanged(oldPosition,newPosition,this));
    }

    public void move(Integer direction){
        switch (direction){
            // FORWARD
            case 0 -> {
                Vector2d newPosition = this.position.add(this.orientation.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    Vector2d newPos = this.map.calculateNewPosition(newPosition);
                    Vector2d oldPosition = this.position;
                    this.position = newPos;
                    positionChanged(oldPosition, newPos);
                }
            }
            // BACKWARD
            case 4 -> {
                Vector2d newPosition = this.position.subtract(this.orientation.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    Vector2d newPos = this.map.calculateNewPosition(newPosition);
                    Vector2d oldPosition = this.position;
                    this.position = newPos;
                    positionChanged(oldPosition, newPos);
                }
            }
        }
        if(direction != 4){
            for(int i=0;i<direction;i++){
                this.orientation = this.orientation.next();
            }
        }
    }
    @Override
    public String toString() {
        return orientation.toString();
    }

    public Energy getEnergy() {
        return energy;
    }
    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    @Override
    public int compareTo(Animal o) {
        return this.getEnergy().getValue().compareTo(o.getEnergy().getValue());
    }

    public Genome getGenome() {
        return genome;
    }
}
