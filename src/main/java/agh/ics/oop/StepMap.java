package agh.ics.oop;

import java.util.*;

public class StepMap extends AbstractWorldMap{

    private final Vector2d leftBottomCorner;
    private final Vector2d rightTopCorner;
    private final Jungle jungle;
    protected final Map<Vector2d,Grass> grasses;

    public StepMap(Integer width, Integer height, Integer jungleRatio) {
        this.grasses = new HashMap<>();
        this.jungle = new Jungle(jungleRatio, width, height, this);
        this.leftBottomCorner = new Vector2d(1,1);
        this.rightTopCorner = new Vector2d(width, height);

        placeInitialAnimals();
    }
    private void placeInitialAnimals(){
        int startAnimals = Parameters.getStartAnimals();
        for (int i=0;i<startAnimals;i++){
            Vector2d pos = this.getFreePos();
            if(pos == null)break;
            this.place(new Animal(this, pos));
        }
    }

    @Override
    public void placeGrass(){
        // place grass in jungle
        Vector2d junglePos = jungle.getFreePos(grasses);
        if (junglePos != null){
            grasses.put(junglePos,new Grass(junglePos));
        }

        // place grass in steps
        Vector2d stepPos = getFreePos();
        if (stepPos != null){
            grasses.put(stepPos, new Grass(stepPos));
        }
    }
    @Override
    public Vector2d getFreePos(){
        List<Vector2d> freePositions = new LinkedList<>();

        for(int i=getLeftBottomCorner().getX();i <= getRightTopCorner().getX(); i++){
            for (int j= getLeftBottomCorner().getY();j<= getRightTopCorner().getY();j++){
                Vector2d vec = new Vector2d(i,j);
                if (!jungle.isInJungle(vec) && !isOccupied(vec)){
                    freePositions.add(vec);
                }
            }
        }
        Collections.shuffle(freePositions);
        for ( Vector2d freePos: freePositions){
            if (!isOccupied(freePos)){
                return freePos;
            }
        }
        return null;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInMap(position);
    }

    public boolean isInMap(Vector2d position){
        return (position.follows(leftBottomCorner) && position.precedes(rightTopCorner));
    }

    @Override
    public Vector2d getLeftBottomCorner() {
        return leftBottomCorner;
    }

    @Override
    public Vector2d getRightTopCorner() {
        return rightTopCorner;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null){
            return super.objectAt(position);
        }
        if (grasses.containsKey(position)){
            return grasses.get(position);
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        return grasses.containsKey(position);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {

        animals.putIfAbsent(newPosition, new LinkedList<>());
        animals.get(newPosition).add(animal);
        Collections.sort(animals.get(newPosition));
        removeAnimalFromMap(oldPosition,animal);
    }
    public void eatGrass(){
        List<Vector2d> eatenGrass = new LinkedList<>();
        int minx = getLeftBottomCorner().getX();
        int miny = getLeftBottomCorner().getY();
        int maxx = getRightTopCorner().getX();
        int maxy = getRightTopCorner().getY();
        for (int x=minx ;x <= maxx; x++){
            for(int y=miny;y<=maxy;y++){
                Vector2d pos = new Vector2d(x,y);
                if (grasses.containsKey(pos)){
                    List<Animal> candidates = objectsAt(pos);
                    if (candidates != null) {
                        if(candidates.size() > 0){
                            candidates.forEach(animal -> {
                                animal.setEnergy(EnergyCalculator.calculatePlantEnergy(animal.getEnergy(), candidates.size()));
                            });
                            eatenGrass.add(pos);
                        }
                    }
                }
            }
        }
        eatenGrass.forEach(grasses::remove);
    }
    @Override
    public void deleteAnimals() {
        List<Animal> animals = getAnimalsOnMap();
        animals.forEach(animal -> {
            if (EnergyCalculator.isTooLowOnEnergy(animal.getEnergy())){
                deleteAnimal(animal);
            }
        });
    }
    @Override
    public void animalsBreed() {
        int minx = getLeftBottomCorner().getX();
        int miny = getLeftBottomCorner().getY();
        int maxx = getRightTopCorner().getX();
        int maxy = getRightTopCorner().getY();
        Animal firstParent,secondParent;
        for (int x=minx;x<=maxx;x++){
            for (int y=miny;y<=maxy;y++){
                List<Animal> animalsOnPos = objectsAt(new Vector2d(x,y));
                if(animalsOnPos == null || animalsOnPos.size() < 2){
                    continue;
                }else{
                    Collections.sort(animalsOnPos);
                    Collections.reverse(animalsOnPos);
                }
                firstParent = animalsOnPos.get(0);
                secondParent = animalsOnPos.get(1);
                if(EnergyCalculator.canBreed(firstParent.getEnergy()) &&  EnergyCalculator.canBreed(secondParent.getEnergy())){
                    Animal child = firstParent.breed(secondParent);
                    place(child);
                    firstParent.incrementChildren();
                    secondParent.incrementChildren();
                    firstParent.addChild(child);
                    secondParent.addChild(child);
                }

            }
        }
    }
    @Override
    public void moveAnimals(){
        List<Animal> animals = getAnimalsOnMap();
        animals.forEach(animal -> {
            animal.move(animal.getGenome().getRandomDirection());
            animal.setEnergy(EnergyCalculator.calculateMoveEnergy(animal.getEnergy()));
        });
    }

    @Override
    protected List<Animal> getAnimalsOnMap (){
        List<Animal> animals = new LinkedList<>();
        int minX = getLeftBottomCorner().getX();
        int minY = getLeftBottomCorner().getY();
        int maxX = getRightTopCorner().getX();
        int maxY = getRightTopCorner().getY();
        for (int x = minX;x <= maxX; x++){
            for (int y = minY; y <= maxY; y++){
                Vector2d pos = new Vector2d(x,y);
                if (isOccupied(pos) && objectsAt(pos) != null){
                    animals.addAll(objectsAt(pos));
                }
            }
        }
        return animals;
    }

    @Override
    public int countAnimals() {
        return getAnimalsOnMap().size();
    }

    @Override
    public int countGrasses() {
        return grasses.size();
    }

    @Override
    public int countAvgEnergy() {
        int counter = 0;
        int energy = 0;
        for(Animal animal : getAnimalsOnMap()){
            energy += animal.getEnergy().getValue();
            counter++;
        }
        if (counter > 0){
            return energy/counter;
        }else return 0;
    }

    @Override
    public int countAvgLifetime() {
        int counter = 0;
        int lifetime = 0;
        for (Animal animal: deadAnimals){
            lifetime += animal.getLifetime();
            counter++;
        }
        if (counter > 0){
            return lifetime/counter;
        }else{
            return 0;
        }
    }

    @Override
    public void incrementLifetime() {
        getAnimalsOnMap().forEach(Animal::incrementLifetime);
    }

    @Override
    public int countAvgChildren() {
        int counter = 0;
        int children = 0;
        for (Animal animal: getAnimalsOnMap()){
            counter++;
            children += animal.getChildren();
        }
        if (counter > 0){
            return children/counter;
        }else{
            return 0;
        }
    }

    @Override
    public Genome countDominantGenome() {
        HashMap<Genome,Integer> ranking = new HashMap<>();
        for(Animal animal: getAnimalsOnMap()){
            ranking.putIfAbsent(animal.getGenome(),1);
            ranking.put(animal.getGenome(), ranking.get(animal.getGenome())+1);
        }
        for(Animal animal: deadAnimals){
            ranking.putIfAbsent(animal.getGenome(),1);
            ranking.put(animal.getGenome(), ranking.get(animal.getGenome())+1);
        }
        return Collections.max(ranking.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Override
    public Vector2d calculateNewPosition(Vector2d newPosition) {
        return newPosition;
    }
}
