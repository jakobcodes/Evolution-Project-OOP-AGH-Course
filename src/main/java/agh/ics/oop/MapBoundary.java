package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver{
    private final SortedSet<Vector2d> objectsX;
    private final SortedSet<Vector2d> objectsY;

    public MapBoundary() {
        this.objectsX = new TreeSet<>(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                if (o1.getX() < o2.getX() || (o1.getX() == o2.getX() && o1.getY() < o2.getY())) return -1;
                else if (o1.getX() > o2.getX() || (o1.getY() > o2.getY())) return 1;
                else return 0;
            }
        });
        this.objectsY = new TreeSet<>(new Comparator<Vector2d>() {
            @Override
            public int compare(Vector2d o1, Vector2d o2) {
                if (o1.getY() < o2.getY() || (o1.getY() == o2.getY() && o1.getX() < o2.getX())) return -1;
                else if (o1.getY() > o2.getY() || (o1.getX() > o2.getX())) return 1;
                else return 0;
            }
        });
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        objectsX.remove(oldPosition);
        objectsX.add(newPosition);

        objectsY.remove(oldPosition);
        objectsY.add(newPosition);
    }

    public Vector2d getLeftBottomCorner(){
        return objectsX.first().lowerLeft(objectsY.first());
    }

    public Vector2d getRightTopCorner(){
        return objectsX.last().upperRight(objectsY.last());
    }

    public void addObject(Vector2d position){
        objectsX.add(position);
        objectsY.add(position);
    }
}