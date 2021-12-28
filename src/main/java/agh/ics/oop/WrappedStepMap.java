package agh.ics.oop;

import java.util.Collections;
import java.util.LinkedList;

public class WrappedStepMap extends StepMap{
    public WrappedStepMap(Integer width, Integer height,  Integer jungleRatio) {
        super(width, height, jungleRatio);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition,Animal animal) {
        Vector2d vec = new Vector2d(newPosition.getX(), newPosition.getY());
        if(!isInMap(newPosition)){
            vec = calculateNewPosition(newPosition);
        }
        if(objectAt(vec) instanceof Grass) {
            grasses.remove(vec);
        }

        animals.putIfAbsent(newPosition, new LinkedList<>());
        animals.get(newPosition).add(animal);
        Collections.sort(animals.get(newPosition));
        removeAnimalFromMap(oldPosition,animal);
    }
    @Override
    public Vector2d calculateNewPosition(Vector2d newPosition) {
        int newX = newPosition.getX();
        int newY = newPosition.getY();
        if (newPosition.getX() > getRightTopCorner().getX()){
            newX = getLeftBottomCorner().getX();
        }
        if (newPosition.getX() < getLeftBottomCorner().getX()){
            newX = getRightTopCorner().getX();
        }
        if (newPosition.getY() > getRightTopCorner().getY()){
            newY = getLeftBottomCorner().getY();
        }
        if (newPosition.getY() < getLeftBottomCorner().getY()){
            newY = getRightTopCorner().getY();
        }
        return new Vector2d(newX, newY);
    }


}



