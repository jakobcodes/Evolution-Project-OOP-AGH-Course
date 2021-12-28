package agh.ics.oop;

public class EnergyCalculator {
    private final static Energy plantEnergy = new Energy(Parameters.getPlantEnergy());
    private final static Energy moveEnergy = new Energy(Parameters.getMoveEnergy());
    private final static Energy startEnergy = new Energy(Parameters.getStartEnergy());

    public static Energy calculatePlantEnergy(Energy currentEnergy, Integer numberOfAnimals){
        Integer calculatedPlantEnergy = plantEnergy.getValue()/numberOfAnimals;
        return currentEnergy.add(new Energy(calculatedPlantEnergy));
    }

    public static Energy calculateMoveEnergy(Energy currentEnergy){
        return currentEnergy.subtract(new Energy(moveEnergy.getValue()));
    }

    public static Energy calculateBreedEnergy(Energy currentEnergy){
        Integer calculatedBreedEnergy = currentEnergy.getValue()/4;
        return currentEnergy.subtract(new Energy(calculatedBreedEnergy));
    }
    public static boolean isTooLowOnEnergy(Energy currentEnergy){
        return currentEnergy.getValue() < moveEnergy.getValue();
    }
    public static boolean canBreed(Energy currentEnergy){
        return currentEnergy.getValue() >= startEnergy.getValue()/2;
    }
}
