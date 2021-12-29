package agh.ics.oop;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.LinkedList;

public class AnimalInfo{
    private final Animal animal;
    private final VBox info;
    private final Label children;
    private final Label progeny;
    private final Label death;

    public AnimalInfo(Animal animal) {
        this.animal = animal;
        Label title = new Label("ANIMAL INFO");
        title.setFont(new Font("Arial", 30));
//        GENOM
        Label genom = new Label(animal.getGenome().toString());
        genom.setFont(new Font("Arial", 20));
//        NUMBER OF CHILDRENS
        children = new Label(String.valueOf(animal.getChildren()));
        children.setFont(new Font("Arial", 20));
        Label childrenTitle = new Label("Number of Childrens: ");
        childrenTitle.setFont(new Font("Arial",20));
        HBox childrenBox = new HBox(childrenTitle, children);
        childrenBox.setAlignment(Pos.CENTER);
//        NUMBER OF PROGENIES
        progeny = new Label(String.valueOf(animal.countChildrenProgeny(new LinkedList<>())));
        progeny.setFont(new Font("Arial", 20));
        Label progenyTitle = new Label("Number of Progenies: ");
        progenyTitle.setFont(new Font("Arial",20));
        HBox progenyBox = new HBox(progenyTitle,progeny);
        progenyBox.setAlignment(Pos.CENTER);
//        LIFETIME
        death = new Label("alive");
        death.setFont(new Font("Arial", 20));


        this.info = new VBox(title,genom,childrenBox,progenyBox,death);
        this.info.setAlignment(Pos.CENTER);
        this.info.setSpacing(20);
    }

    public void update() {
        children.setText(String.valueOf(animal.getChildren()));
        progeny.setText(String.valueOf(animal.countChildrenProgeny(new LinkedList<>())));
        if(animal.getEnergy().isDead()){
            death.setText(String.valueOf(animal.getLifetime()));
        }
    }

    public VBox getInfo() {
        return info;
    }
}
