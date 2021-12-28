package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement, Comparable<Animal>{
    private MapDirection orientation;
    private Vector2d position;
    private final AbstractWorldMap map;
    private final List<IPositionChangeObserver<Animal>> observers = new ArrayList<>();
    private Energy energy;
    private final Genome genome;
    private int lifetime;
    private int children;

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
        this.lifetime = 0;
        this.children = 0;
    }

    public MapDirection getOrientation() { return orientation; }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getPathToImage() {
        if (energy.getValue() >= Parameters.getStartEnergy()){
            return "src/main/resources/animal-green.png";
        } else if (energy.getValue() >= Parameters.getStartEnergy()/2) {
            return "src/main/resources/animal-yellow.png";
        }else{
            return "src/main/resources/animal-red.png";
        }
    }

    public void addObserver(IPositionChangeObserver<Animal> observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver<Animal> observer){
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
    public Animal breed(Animal other){
        Genome childGenome;
        Energy childEnergy;
        Energy thisAnimalEnergy = this.getEnergy();
        Energy otherAnimalEnergy = other.getEnergy();
        Float fatherPercent = thisAnimalEnergy.getPercent(otherAnimalEnergy);
        int thisNumberGenes = Math.round((fatherPercent * 32 * 100)/100);
        int otherNumberGenes = 32 - thisNumberGenes;
        if(thisNumberGenes > otherNumberGenes){
            childGenome = this.getGenome().createMixedGenome(other.getGenome(), otherNumberGenes);
        }else{
            childGenome = other.getGenome().createMixedGenome(this.getGenome(), thisNumberGenes);
        }
        Energy e1 = new Energy(0).addQuarter(thisAnimalEnergy);
        Energy e2 = new Energy(0).addQuarter(otherAnimalEnergy);
        childEnergy = e1.add(e2);
        this.setEnergy(EnergyCalculator.calculateBreedEnergy(this.getEnergy()));
        other.setEnergy(EnergyCalculator.calculateBreedEnergy(other.getEnergy()));

        return new Animal(this.map, this.getPosition(), childGenome,childEnergy);
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

    public int getLifetime() {
        return lifetime;
    }
    public void incrementLifetime(){
        this.lifetime++;
    }
    public void incrementChildren(){
        this.children++;
    }

    public int getChildren() {
        return children;
    }
}
