package agh.ics.oop;

public class Parameters {
    private static int START_ENERGY;
    private static int MOVE_ENERGY;
    private static int PLANT_ENERGY;



    private static int START_ANIMALS;
    private static boolean SEFlag = false;
    private static boolean MEFlag = false;
    private static boolean PEFlag = false;

    public static int getStartEnergy() {
        return START_ENERGY;
    }

    public static int getMoveEnergy() {
        return MOVE_ENERGY;
    }

    public static int getPlantEnergy() {
        return PLANT_ENERGY;
    }
    public static int getStartAnimals() {
        return START_ANIMALS;
    }
    public static void setStartEnergy(int value){
        if(!SEFlag){
            START_ENERGY = value;
            SEFlag = true;
        }
    }
    public static void setMoveEnergy(int value){
        if(!MEFlag){
            MOVE_ENERGY = value;
            MEFlag = true;
        }
    }
    public static void setPlantEnergy(int value){
        if(!PEFlag){
            PLANT_ENERGY = value;
            PEFlag = true;
        }
    }
}
