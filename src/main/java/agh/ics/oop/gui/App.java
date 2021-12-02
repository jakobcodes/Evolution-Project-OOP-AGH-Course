package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        AbstractWorldMap map = new GrassField(10);
        createAxes(grid,map);
        addObjects(grid,map);

        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createAxes(GridPane grid, AbstractWorldMap map){
        Integer minX = map.getLeftBottomCorner().x;
        Integer minY = map.getLeftBottomCorner().y;
        Integer maxX = map.getRightTopCorner().x;
        Integer maxY = map.getRightTopCorner().y;

        Label yx = new Label("y/x");
        grid.add(yx, 0, 0,1,1);
        GridPane.setHalignment(yx, HPos.CENTER);

        grid.getColumnConstraints().add(new ColumnConstraints(25));
        grid.getRowConstraints().add(new RowConstraints(25));
        for(Integer i = minX; i<= maxX;i++ ){
            Label label = new Label(i.toString());
            grid.add(label, i-minX+1, 0,1,1);
            grid.getColumnConstraints().add(new ColumnConstraints(25));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for(Integer i = maxY; i >= minY;i--){
            Label label = new Label(i.toString());
            grid.add(label, 0, maxY-i+1,1,1);
            grid.getRowConstraints().add(new RowConstraints(25));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }
    public void addObjects(GridPane grid, AbstractWorldMap map){
        Integer minX = map.getLeftBottomCorner().x;
        Integer minY = map.getLeftBottomCorner().y;
        Integer maxX = map.getRightTopCorner().x;
        Integer maxY = map.getRightTopCorner().y;

        for(Integer x = minX; x<=maxX ; x++){
            for (Integer y = minY; y<=maxY ; y++){
                Vector2d pos = new Vector2d(x,y);
                if (map.isOccupied(pos)){
                    Object object = map.objectAt(pos);
                    Label label = new Label(object.toString());
                    grid.add(label, x-minX+1, maxY+1-y,1,1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }
    }
}
