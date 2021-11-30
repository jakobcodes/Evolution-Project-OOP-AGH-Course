package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap{

    private Integer grassQuantity;
    private Integer grassBorder;
    private List<Grass> grasses;

    public GrassField(Integer grassQuantity) {
        this.grassQuantity = grassQuantity;
        this.grasses = new ArrayList<Grass>();
        this.grassBorder = (int) Math.floor(Math.sqrt(grassQuantity*10));

        while (grassQuantity > 0){
            if (placeGrass()){
                grassQuantity--;
            }
        }
    }

    public boolean placeGrass(){
        int randomX = (int) (Math.random() * grassBorder + 1);
        int randomY = (int) (Math.random() * grassBorder + 1);
        Vector2d grassPos = new Vector2d(randomX, randomY);
        if (!isOccupied(grassPos)){
            if (!(objectAt(grassPos) instanceof Grass)){
                grasses.add(new Grass(grassPos));
                if (this.leftBottomCorner == null) this.leftBottomCorner = new Vector2d(grassPos.x, grassPos.y);
                if (this.rightTopCorner == null) this.rightTopCorner = new Vector2d(grassPos.x, grassPos.y);
                checkMapBorders(grassPos);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (super.canMoveTo(position)){
            checkMapBorders(position);
            return true;
        }
        return (objectAt(position) instanceof Grass);
    }

    @Override
    public boolean place(Animal animal) {
        if (super.place(animal)){
            checkMapBorders(animal.getPosition());
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        for (Grass grass: grasses){
            if (grass.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) return super.objectAt(position);  // podwójne wywołanie, a ta metoda jest akurat dość kosztowna
        if(isOccupied(position)){
            for (Grass grass: grasses){
                if (grass.getPosition().equals(position)) return grass;
            }
        }
        return null;
    }

    public void checkMapBorders(Vector2d pos){  // czemu public?
        this.leftBottomCorner = this.leftBottomCorner.lowerLeft(pos);
        this.rightTopCorner = this.rightTopCorner.upperRight(pos);
    }

    public List<Grass> getGrasses() {   // ten getter nie ma prawa bytu
        return grasses;
    }

    public Integer getGrassQuantity() {
        return grassQuantity;
    }

    public Integer getGrassBorder() {
        return grassBorder;
    }
}
