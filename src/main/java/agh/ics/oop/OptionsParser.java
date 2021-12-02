package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {

    public List<MoveDirection> parse(String[] directions){
        List<MoveDirection> moveDirections= new LinkedList<>();
        
        for(String direction: directions){
            switch(direction){
                case "f", "forward" -> moveDirections.add(MoveDirection.FORWARD);
                case "b", "backward" -> moveDirections.add(MoveDirection.BACKWARD);
                case "r", "right" -> moveDirections.add(MoveDirection.RIGHT);
                case "l", "left" -> moveDirections.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(direction + " is not legal move specification");
            }
        }
        return moveDirections;
    }
}
