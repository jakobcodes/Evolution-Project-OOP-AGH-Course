package agh.ics.oop;

import java.util.*;

public class Jungle {
    private final StepMap map;
    private final Vector2d leftBottomCorner;
    private final Vector2d rightTopCorner;

    public Jungle(Integer jungleRatio, Integer mapWidth, Integer mapHeight, StepMap map) {
        this.leftBottomCorner = calculateLeftBottomCorner(jungleRatio,mapWidth,mapHeight);
        this.rightTopCorner = calculateRightTopCorner(jungleRatio, mapWidth, mapHeight);
        this.map = map;
    }

    private Vector2d calculateLeftBottomCorner (Integer jungleRatio,Integer mapWidth, Integer mapHeight){
        Integer jungleWidth = mapWidth / jungleRatio;
        Integer jungleHeight = mapHeight / jungleRatio;

        return new Vector2d(
                (mapWidth - jungleWidth)/2 + 1,
                (mapHeight - jungleHeight)/2 + 1
        );
    }
    private Vector2d calculateRightTopCorner (Integer jungleRatio,Integer mapWidth, Integer mapHeight){
        int jungleWidth = mapWidth / jungleRatio;
        int jungleHeight = mapHeight / jungleRatio;

        return new Vector2d(
                (mapWidth - jungleWidth)/2 + jungleWidth,
                (mapHeight - jungleHeight)/2 + jungleHeight
        );
    }

    public Vector2d getFreePos(Map<Vector2d,Grass> grassPositions){
        List<Vector2d> freePositions = new LinkedList<>();

        for(int i=leftBottomCorner.getX();i <= rightTopCorner.getX(); i++){
            for (int j= leftBottomCorner.getY();j<= rightTopCorner.getY();j++){
                Vector2d vec = new Vector2d(i,j);
                if (!grassPositions.containsKey(vec)){
                    freePositions.add(vec);
                }
            }
        }
        Collections.shuffle(freePositions);
        for ( Vector2d freePos: freePositions){
            if (!map.isOccupied(freePos)){
                return freePos;
            }
        }
        return null;
    }

    public boolean isInJungle(Vector2d position){
        return (position.follows(leftBottomCorner) && position.precedes(rightTopCorner));
    }
}
