package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private VBox vBox;

    public VBox getvBox() {
        return vBox;
    }

    public GuiElementBox(IMapElement iMapElement) {
        try {
            Image image = new Image(new FileInputStream(iMapElement.getPathToImage()));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            Label label;
//            if (iMapElement instanceof Animal) label = new Label("Z ("+ iMapElement.getPosition().getX() + "," + iMapElement.getPosition().getY() + ")");
//            else label = new Label("Trawa");
            this.vBox = new VBox(imageView);
            this.vBox.setAlignment(Pos.CENTER);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
