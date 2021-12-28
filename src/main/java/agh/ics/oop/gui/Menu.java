package agh.ics.oop.gui;

import agh.ics.oop.Parameter;
import agh.ics.oop.Parameters;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Menu {
    private final Scene scene;
    private final BorderPane borderPane;
    private final Button startButton;
    private final Map<Parameter,TextField> parameters;

    public Menu() {
        this.borderPane = new BorderPane();
        this.parameters = new HashMap<>();
        this.startButton = new Button("start");


        createMenu();
        this.scene = new Scene(borderPane,1280,720);
    }

    private void createMenu(){
//        Parameters
        VBox parameters = new VBox(
                addParameter(Parameter.WIDTH),
                addParameter(Parameter.HEIGHT),
                addParameter(Parameter.START_ENERGY),
                addParameter(Parameter.MOVE_ENERGY),
                addParameter(Parameter.PLANT_ENERGY),
                addParameter(Parameter.START_ANIMALS),
                addParameter(Parameter.JUNGLE_RATIO)
        );
//        Title
        Label menuName = new Label("MENU");
        menuName.setFont(new Font("Arial", 30));
//
//        Start button
        HBox buttonBox = new HBox(startButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox menu = new VBox(
                menuName,
                parameters,
                buttonBox
        );
        menu.setSpacing(50);
        parameters.setSpacing(25);
        menu.setAlignment(Pos.CENTER);
        this.borderPane.setCenter(menu);
    }
    private HBox addParameter(Parameter parameter){
        Label name = new Label(parameter.toString());
        TextField input = new TextField();
        HBox hbox = new HBox(name, input);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        input.setText(Parameters.getValueAsString(parameter));
        parameters.put(parameter, input);
        return hbox;
    }
    public void submitInputs(){
        parameters.forEach((key,value) -> {
            switch (key){
                case WIDTH -> {
                    Parameters.setWIDTH(Integer.parseInt(value.getText()));
                }
                case HEIGHT -> {
                    Parameters.setHEIGHT(Integer.parseInt(value.getText()));
                }
                case START_ENERGY -> {
                    Parameters.setStartEnergy(Integer.parseInt(value.getText()));
                }
                case MOVE_ENERGY -> {
                    Parameters.setMoveEnergy(Integer.parseInt(value.getText()));
                }
                case PLANT_ENERGY -> {
                    Parameters.setPlantEnergy(Integer.parseInt(value.getText()));
                }
                case START_ANIMALS -> {
                    Parameters.setStartAnimals(Integer.parseInt(value.getText()));
                }
                case JUNGLE_RATIO -> {
                    Parameters.setJungleRatio(Integer.parseInt(value.getText()));
                }
            }
        });
    }

    public Button getStartButton() {
        return startButton;
    }

    public Scene getScene() {
        return scene;
    }
}
