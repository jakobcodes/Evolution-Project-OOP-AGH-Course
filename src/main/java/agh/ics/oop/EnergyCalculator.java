package agh.ics.oop;

public class EnergyCalculator {
    private final static Energy plantEnergy = new Energy(Parameters.getPlantEnergy());
    private final static Energy moveEnergy = new Energy(Parameters.getMoveEnergy());

    public static Energy calculatePlantEnergy(Energy currentEnergy, Integer numberOfAnimals){
        Integer calculatedPlantEnergy = plantEnergy.getValue()/numberOfAnimals;
        return currentEnergy.add(new Energy(calculatedPlantEnergy));
    }

    public static Energy calculateMoveEnergy(Energy currentEnergy){
        return currentEnergy.subtract(new Energy(moveEnergy.getValue()));
    }

    public static Energy calculateBreedEnergy(Energy currentEnergy){
        Integer calculatedPlantEnergy = currentEnergy.getValue()/4;
        return currentEnergy.subtract(new Energy(calculatedPlantEnergy));
    }

}
