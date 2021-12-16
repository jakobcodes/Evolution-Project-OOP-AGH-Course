package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

import static java.lang.System.exit;

public class App extends Application implements IPositionChangeObserver{
    private AbstractWorldMap map;
    private GridPane grid;
    private ThreadedSimulationEngine engine;
    private int moveDelay;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        try {
            this.grid = new GridPane();
            this.grid.setGridLinesVisible(true);
            map = new GrassField(10);
//            String[] args = new String[]{"f", "f" ,"r" , "f", "f", "f", "f", "r", "r", "f", "f", "f", "f", "f"};
//            List<MoveDirection> directions = new OptionsParser().parse(args);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            this.engine = new ThreadedSimulationEngine(map, positions);
            this.engine.addObserver(this);
            this.moveDelay = 1000;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        createInterface();
        createAxes();
        addObjects();


        Scene scene = new Scene(this.grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void createInterface(){
        TextField textField = new TextField();
        Button startButton = new Button("start");
        startButton.setOnAction((event) -> {
            String[] dir = textField.getText().split("");
            List<MoveDirection> directions = new OptionsParser().parse(dir);
            this.engine.setDirections(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });
        HBox hBox = new HBox(startButton, textField);
        this.grid.add(hBox, 0,0,4,1);
        GridPane.setHalignment(hBox, HPos.CENTER);
        this.grid.getColumnConstraints().add(new ColumnConstraints(50));
        this.grid.getRowConstraints().add(new RowConstraints(50));
    }

    public void createAxes(){


        Integer minX = this.map.getLeftBottomCorner().x;
        Integer minY = this.map.getLeftBottomCorner().y;
        Integer maxX = this.map.getRightTopCorner().x;
        Integer maxY = this.map.getRightTopCorner().y;

        Label yx = new Label("y/x");
        this.grid.add(yx, 0, 1,1,1);
        GridPane.setHalignment(yx, HPos.CENTER);

        this.grid.getColumnConstraints().add(new ColumnConstraints(50));
        this.grid.getRowConstraints().add(new RowConstraints(50));
        for(Integer i = minX; i<= maxX;i++ ){
            Label label = new Label(i.toString());
            this.grid.add(label, i-minX+1, 1,1,1);
            this.grid.getColumnConstraints().add(new ColumnConstraints(50));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for(Integer i = maxY; i >= minY;i--){
            Label label = new Label(i.toString());
            this.grid.add(label, 0, maxY-i+1+1,1,1);
            this.grid.getRowConstraints().add(new RowConstraints(50));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }
    public void addObjects(){
        int minX = map.getLeftBottomCorner().x;
        int minY = map.getLeftBottomCorner().y;
        int maxX = map.getRightTopCorner().x;
        int maxY = map.getRightTopCorner().y;

        for(int x = minX; x<=maxX ; x++){
            for (int y = minY; y<=maxY ; y++){
                Vector2d pos = new Vector2d(x,y);
                if (map.isOccupied(pos)){
                    IMapElement iMapElement = (IMapElement) map.objectAt(pos);
                    this.grid.add(new GuiElementBox(iMapElement).getvBox(), x-minX+1, maxY+1-y+1,1,1);
                }
            }
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        try {
            Thread.sleep(moveDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
//            Node node = this.grid.getChildren().get(0);
//            grid.getChildren().clear();
//            this.grid.getChildren().add(node);
            this.grid.getChildren().retainAll(grid.getChildren().get(0));
            createInterface();
            createAxes();
            addObjects();
        });
    }

}
