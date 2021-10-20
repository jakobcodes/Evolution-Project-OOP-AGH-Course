package agh.ics.oop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal");

        String[] arguments = {"f", "f", "l", "c", "b", "c"};
        List<Direction> directions = convertInstructions(arguments);
        run(directions);

        System.out.println("System zakonczyl działanie");
    }

    public static void run(List<Direction> directions) {
        System.out.println("Start");

        directions
                .forEach(move -> {
                    switch (move){
                        case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                        case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                        case RIGHT -> System.out.println("Zwierzak skreca w prawo");
                        case LEFT -> System.out.println("Zwierzak skreca w lewo");
                    }
                });

        System.out.println("Stop");
    }

    public static List<Direction> convertInstructions(String[] moves) {

        Stream<String> directionsStream = Stream.of(moves);
        List<String> directionList = directionsStream
                .filter(e -> e.equals("f") || e.equals("b") || e.equals("r") || e.equals("l"))
                .collect(Collectors.toList());

        return directionList
                .stream()
                .map(x -> {
                    return switch (x) {
                        case "f" -> Direction.FORWARD;
                        case "b" -> Direction.BACKWARD;
                        case "r" -> Direction.RIGHT;
                        default -> Direction.LEFT;
                    };
                })
                .toList();
    }

}
