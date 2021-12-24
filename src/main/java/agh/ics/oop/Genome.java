package agh.ics.oop;

import java.util.*;

public class Genome {
    private static final int GENOME_SIZE = 32;

    private final List<Gene> genes;

    public Genome() {
        this.genes = getRandomGenome();
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

    @Override
    public String toString() {
        return "Genome{" +
                "genes=" + genes +
                '}';
    }
}
