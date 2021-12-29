package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.IDayFinishedObserver;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MapInfo implements IDayFinishedObserver {

    private final AbstractWorldMap map;
    private VBox mapInfo;
    private Label dominantGenomeLabel;


    public MapInfo(AbstractWorldMap map) {
        this.map = map;
        this.dominantGenomeLabel = new Label(this.map.countDominantGenome().toString());

        this.mapInfo = new VBox(dominantGenomeLabel);
    }

    @Override
    public void dayFinished() {
        update();
    }
    private void update(){
        dominantGenomeLabel.setText(this.map.countDominantGenome().toString());
    }

    public VBox getMapInfo() {
        return mapInfo;
    }
}
