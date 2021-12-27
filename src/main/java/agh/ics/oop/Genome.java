package agh.ics.oop;

import java.util.*;

public class Genome {
    private static final int GENOME_SIZE = 32;

    private final List<Gene> genes;

    public Genome() {
        this.genes = getRandomGenome();
    }

    public Genome(List<Gene> genes){
        this.genes = genes;
    }

    public int getRandomDirection(){
        Random random = new Random();
        return this.genes.get(random.nextInt(GENOME_SIZE)).getValue();
    }

    private List<Gene> getRandomGenome(){
        List<Gene> list = new LinkedList<>();
        for(int i=0;i<GENOME_SIZE;i++){
            Random random = new Random();
            list.add(new Gene(random.nextInt(8)));
        }
        list.sort(new Comparator<Gene>() {
            @Override
            public int compare(Gene o1, Gene o2) {
                return Integer.compare(o1.getValue(),o2.getValue());
            }
        });
        return list;
    }
    private List<Gene> getLeft(Integer size){
        return this.genes.subList(0,size-1);
    }
    private List<Gene> getRight(Integer size){
        return this.genes.subList(31-size, 31);
    }

    public Genome createMixedGenome(Genome other, Integer otherGenesNumber){
        List<Gene> genes = new LinkedList<>();
        List<Gene> left,right;

        Random random = new Random();
        if(random.nextBoolean()){
            left = getLeft(32-otherGenesNumber);
            right = other.getRight(otherGenesNumber);
        }else{
            left = other.getLeft(otherGenesNumber);
            right = getRight(32-otherGenesNumber);
        }
        left.addAll(right);
        return new Genome(left);

    }

    @Override
    public String toString() {
        return "Genome{" +
                "genes=" + genes +
                '}';
    }
}
