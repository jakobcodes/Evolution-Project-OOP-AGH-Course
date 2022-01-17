package agh.ics.oop;

import java.util.*;

public class Genome {
    private static final int GENOME_SIZE = 32;

    private final List<Gene> genes;

    public Genome() {
        this.genes = getRandomGenome();
    }

    public Genome(List<Gene> genes) {
        this.genes = genes;
    }

    public int getRandomDirection() {
        Random random = new Random();   // nowy obiekt co wywołanie
        Collections.shuffle(this.genes);
        int randomValue = this.genes.get(0).getValue(); // nie praktyczniej jest losować indeks, niż tasować całą listę?
        Collections.sort(this.genes);
        return randomValue;
    }

    private List<Gene> getRandomGenome() {  // ta metoda jest używana tylko w konstruktorze jednolinijkowym, nie lepiej przenieść tam jej zawartość?
        List<Gene> list = new LinkedList<>();
        for (int i = 0; i < GENOME_SIZE; i++) {
            Random random = new Random();
            list.add(new Gene(random.nextInt(8)));
        }
        list.sort(new Comparator<Gene>() {
            @Override
            public int compare(Gene o1, Gene o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });
        return list;
    }

    private List<Gene> getLeft(Integer size) {  // https://docs.oracle.com/javase/8/docs/api/java/util/List.html#subList-int-int-
        List<Gene> leftList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            leftList.add(this.genes.get(i));
        }
        return leftList;
    }

    private List<Gene> getRight(Integer size) {
        List<Gene> rightList = new LinkedList<>();
        for (int i = size; i < 32; i++) {
            rightList.add(this.genes.get(i));
        }
        return rightList;
    }

    public Genome createMixedGenome(Genome other, Integer otherGenesNumber) {
        List<Gene> left, right;

        Random random = new Random();   // j.w.
        if (random.nextBoolean()) {
            left = getLeft(otherGenesNumber);
            right = other.getRight(otherGenesNumber);
        } else {
            left = other.getLeft(otherGenesNumber);
            right = getRight(otherGenesNumber);
        }
        left.addAll(right);
        Collections.sort(left);
        return new Genome(left);
    }

    @Override
    public String toString() {
        return "Genome{" +
                "genes=" + genes +
                '}';
    }
}
