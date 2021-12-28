package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.IDayFinishedObserver;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class Chart implements IDayFinishedObserver {
    private final LineChart<Number,Number> lineChart;
    private final AbstractWorldMap map;
    private int daysCounter;
    private final XYChart.Series<Number,Number> animalSeries;
    private final XYChart.Series<Number,Number> grassSeries;
    private final XYChart.Series<Number,Number> avgEnergySeries;
    private final XYChart.Series<Number,Number> avgLifetimeSeries;
    private final XYChart.Series<Number,Number> avgChildrenSeries;

    public Chart(AbstractWorldMap map) {
        this.map = map;
        this.daysCounter = 0;
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("days");

        this.lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        this.animalSeries = new XYChart.Series<>();
        animalSeries.setName("Animals");
        this.grassSeries = new XYChart.Series<>();
        grassSeries.setName("Grasses");
        this.avgEnergySeries = new XYChart.Series<>();
        avgEnergySeries.setName("AVG Energy");
        this.avgLifetimeSeries = new XYChart.Series<>();
        avgLifetimeSeries.setName("AVG Lifetime");
        this.avgChildrenSeries = new XYChart.Series<>();
        avgChildrenSeries.setName("AVG Children");

        addSeries();
    }
    private void addSeries(){
        this.lineChart.getData().add(animalSeries);
        this.lineChart.getData().add(grassSeries);
        this.lineChart.getData().add(avgEnergySeries);
        this.lineChart.getData().add(avgLifetimeSeries);
        this.lineChart.getData().add(avgChildrenSeries);
    }

    @Override
    public void dayFinished() {
        daysCounter++;
        update();
    }

    private void update() {
        animalSeries.getData().add(new XYChart.Data<>(daysCounter,map.countAnimals()));
        grassSeries.getData().add(new XYChart.Data<>(daysCounter,map.countGrasses()));
        avgEnergySeries.getData().add(new XYChart.Data<>(daysCounter,map.countAvgEnergy()));
        avgLifetimeSeries.getData().add(new XYChart.Data<>(daysCounter,map.countAvgLifetime()));
        avgChildrenSeries.getData().add(new XYChart.Data<>(daysCounter,map.countAvgChildren()));
    }
    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }
}
