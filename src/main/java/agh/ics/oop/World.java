package agh.ics.oop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {
    public static void main(String[] args) {

        List<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
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
