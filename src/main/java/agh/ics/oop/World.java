package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.List;

import static java.lang.System.exit;

public class World {
    public static void main(String[] args) {
        Application.launch(App.class, args);
        try {
            List<MoveDirection> directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            exit(0);
        }
    }
}
