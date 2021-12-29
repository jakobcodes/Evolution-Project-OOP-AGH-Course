package agh.ics.oop;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Genome {
    private static final int GENOME_SIZE = 32;

    private final List<Gene> genes;

    public Genome() {
        this.genes = getRandomGenome();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (Gene gene: genes){
            hash = 31 * hash * gene.getValue();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)return true;
        if (!(obj instanceof Genome)){
            return false;
        }

        Genome g = (Genome) obj;

        HashMap<Integer,Integer> hashMap1 = new HashMap<>();
        HashMap<Integer,Integer> hashMap2 = new HashMap<>();
        genes.forEach(gene -> {
            hashMap1.putIfAbsent(gene.getValue(), 0);
            hashMap1.put(gene.getValue(), hashMap1.get(gene.getValue())+1);
        });
        g.genes.forEach(gene -> {
            hashMap2.putIfAbsent(gene.getValue(), 0);
            hashMap2.put(gene.getValue(), hashMap2.get(gene.getValue())+1);
        });
        AtomicBoolean areEqual = new AtomicBoolean(true);
        hashMap1.forEach((key,value) -> {
            if(hashMap2.get(key) != null){
                if (!(hashMap2.get(key).equals(value))) areEqual.set(false);
            }

        });
        return areEqual.get();
    }

    public Genome(List<Gene> genes){
        this.genes = genes;
    }

    public int getRandomDirection(){
        Random random = new Random();
        Collections.shuffle(this.genes);
        int randomValue = this.genes.get(0).getValue();
        Collections.sort(this.genes);
        return randomValue;
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
        List<Gene> leftList = new LinkedList<>();
        for (int i=0;i<size;i++){
            leftList.add(this.genes.get(i));
        }
        return leftList;
    }
    private List<Gene> getRight(Integer size){
        List<Gene> rightList = new LinkedList<>();
        for (int i=size;i<32;i++){
            rightList.add(this.genes.get(i));
        }
        return rightList;
    }

    public Genome createMixedGenome(Genome other, Integer otherGenesNumber){
        List<Gene> left,right;

        Random random = new Random();
        if(random.nextBoolean()){
            left = getLeft(otherGenesNumber);
            right = other.getRight(otherGenesNumber);
        }else{
            left = other.getLeft(otherGenesNumber);
            right = getRight(otherGenesNumber);
        }
        left.addAll(right);
        Collections.sort(left);
        return new Genome(left);
    }

    @Override
    public String toString() {
        return "Genome{"
                 + genes +
                '}';
    }
}
