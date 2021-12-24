package agh.ics.oop;

import java.util.*;

public class StepMap extends RectangularMap{

    private final Jungle jungle;
    protected final Map<Vector2d,Grass> grasses;

    public StepMap(Integer width, Integer height, Integer jungleRatio) {
        super(width, height);
        this.grasses = new HashMap<>();
        this.jungle = new Jungle(jungleRatio, width, height, this);
    }

    @Override
    public void placeGrass(){
        // place grass in jungle
        Vector2d junglePos = jungle.getFreePos(grasses);
        System.out.println(junglePos);
        if (junglePos != null){
            grasses.put(junglePos,new Grass(junglePos));
        }

        // place grass in steps
        Vector2d stepPos = getFreePos();
        System.out.println(stepPos);
        if (stepPos != null){
            grasses.put(stepPos, new Grass(stepPos));
        }
        System.out.println(grasses);
    }
    @Override
    public Vector2d getFreePos(){
        List<Vector2d> freePositions = new LinkedList<>();

        for(int i=getLeftBottomCorner().getX();i <= getRightTopCorner().getX(); i++){
            for (int j= getLeftBottomCorner().getY();j<= getRightTopCorner().getY();j++){
                Vector2d vec = new Vector2d(i,j);
                if (!jungle.isInJungle(vec) && !isOccupied(vec)){
                    freePositions.add(vec);
                }
            }
        }
        Collections.shuffle(freePositions);
        for ( Vector2d freePos: freePositions){
            if (!isOccupied(freePos)){
                return freePos;
            }
        }
        return null;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.canMoveTo(position)){
            return true;
        }
        return (objectAt(position) instanceof Grass);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) return super.objectAt(position);
        return grasses.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        return grasses.containsKey(position);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        if(objectAt(newPosition) instanceof Grass) {
            grasses.remove(newPosition);
        }

        animals.putIfAbsent(newPosition, new LinkedList<>());
        animals.get(newPosition).add(animal);
        Collections.sort(animals.get(newPosition));
        removeAnimalFromMap(oldPosition,animal);
    }
}
