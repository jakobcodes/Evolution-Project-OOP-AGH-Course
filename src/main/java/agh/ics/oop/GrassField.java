package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{

    private final Integer grassQuantity;
    private final Integer grassBorder;
    private final Map<Vector2d,Grass> grasses;

    public GrassField(Integer grassQuantity) {
        this.grassQuantity = grassQuantity;
        this.grasses = new HashMap<>();
        this.grassBorder = (int) Math.floor(Math.sqrt(grassQuantity*10));

        while (grassQuantity > 0){
            if (placeGrass()){
                grassQuantity--;
            }
        }
    }

    public boolean placeGrass(){
        int randomX = (int) (Math.random() * grassBorder + 1);
        int randomY = (int) (Math.random() * grassBorder + 1);
        Vector2d grassPos = new Vector2d(randomX, randomY);
        if (!isOccupied(grassPos)){
            if (!(objectAt(grassPos) instanceof Grass)){
                grasses.put(grassPos,new Grass(grassPos));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.canMoveTo(position)){
            return true;
        }
        return (objectAt(position) instanceof Grass);
    }

    @Override
    public boolean place(Animal animal) {
        if (super.place(animal)){
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        return grasses.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) return super.objectAt(position);
        return grasses.get(position);
    }

    public Vector2d getLeftBottomCorner(){
        Vector2d minXAnimal = animals.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o1.x - o2.x;
            }
        }).get();
        Vector2d minYAnimal = animals.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o1.y - o2.y;
            }
        }).get();
        Vector2d minXGrass = grasses.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o1.x - o2.x;
            }
        }).get();
        Vector2d minYGrass = grasses.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o1.y - o2.y;
            }
        }).get();

        return minXAnimal.lowerLeft(minYAnimal).lowerLeft(minXGrass).lowerLeft(minYGrass);
    }
    public Vector2d getRightTopCorner(){
        Vector2d maxXAnimal = animals.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o2.x - o1.x;
            }
        }).get();
        Vector2d maxYAnimal = animals.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o2.y - o1.y;
            }
        }).get();
        Vector2d maxXGrass = grasses.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o2.x - o1.x;
            }
        }).get();
        Vector2d maxYGrass = grasses.keySet().stream().min(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                return o2.y - o1.y;
            }
        }).get();
        return maxXAnimal.upperRight(maxYAnimal).upperRight(maxXGrass).upperRight(maxYGrass);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(getLeftBottomCorner(), getRightTopCorner());
    }
}
