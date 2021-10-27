package agh.ics.oop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {
    public static void main(String[] args) {

        Animal animal = new Animal();
        System.out.println(animal);
        OptionsParser parser = new OptionsParser();
        List<MoveDirection> moveDirections = parser.parse(args);
        for(MoveDirection moveDirection: moveDirections){
            animal.move(moveDirection);
        }
        System.out.println(animal);
    }

    public static void run(List<MoveDirection> directions) {
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

    public static List<MoveDirection> convertInstructions(String[] moves) {

        Stream<String> directionsStream = Stream.of(moves);
        List<String> directionList = directionsStream
                .filter(e -> e.equals("f") || e.equals("b") || e.equals("r") || e.equals("l"))
                .collect(Collectors.toList());

        return directionList
                .stream()
                .map(x -> switch (x) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    default -> MoveDirection.LEFT;
                })
                .toList();
    }

}
