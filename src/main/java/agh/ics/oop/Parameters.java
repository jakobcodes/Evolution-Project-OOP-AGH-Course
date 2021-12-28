package agh.ics.oop;

public class Parameters {
    private static int WIDTH = 10;
    private static int HEIGHT = 10;
    private static int START_ENERGY = 50;
    private static int MOVE_ENERGY = 1;
    private static int PLANT_ENERGY = 10;
    private static int START_ANIMALS = 10;
    private static int JUNGLE_RATIO = 2;

    private static boolean SEFlag = false;
    private static boolean MEFlag = false;
    private static boolean PEFlag = false;
    private static boolean WFlag = false;
    private static boolean HFlag = false;
    private static boolean JRFlag = false;
    private static boolean SAFlag = false;

    public static int getWIDTH() {return WIDTH;}
    public static int getHEIGHT() {return HEIGHT;}
    public static int getJungleRatio() {return JUNGLE_RATIO;}
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

    public static String getValueAsString(Parameter parameter){
        switch (parameter){
            case WIDTH -> {
                return Integer.toString(getWIDTH());
            }
            case HEIGHT -> {
                return Integer.toString(getHEIGHT());
            }
            case MOVE_ENERGY -> {
                return Integer.toString(getMoveEnergy());
            }
            case PLANT_ENERGY -> {
                return Integer.toString(getPlantEnergy());
            }
            case START_ENERGY -> {
                return Integer.toString(getStartEnergy());
            }
            case START_ANIMALS -> {
                return Integer.toString(getStartAnimals());
            }
            case JUNGLE_RATIO -> {
                return Integer.toString(getJungleRatio());
            }
            default -> {return "";}
        }
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

    public static void setWIDTH(int value) {
        if(!WFlag){
            WIDTH = value;
            WFlag = true;
        }
    }

    public static void setHEIGHT(int value) {
        if(!HFlag){
            HEIGHT = value;
            HFlag = true;
        }
    }

    public static void setStartAnimals(int value) {
        if(!SAFlag){
            START_ANIMALS = value;
            SAFlag = true;
        }
    }

    public static void setJungleRatio(int value) {
        if(!JRFlag){
            JUNGLE_RATIO = value;
            JRFlag = true;
        }
    }
}
