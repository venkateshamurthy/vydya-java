package arrays;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;
import static java.util.AbstractMap.SimpleEntry;
import static java.util.AbstractMap.SimpleImmutableEntry;

public class ArrayProblems {
    final int[] unsorted = Random.from(RandomGenerator.getDefault())
            .ints(1, 100).toArray();
    final int[] sorted = Arrays.stream(unsorted).sorted().toArray();

    public Map.Entry<Integer,Integer> findMinimaMaxima() {
        switch(unsorted.length) {
            case 0: return new SimpleImmutableEntry<>(-1, -1);
            case 1: return new SimpleImmutableEntry<>(unsorted[0], unsorted[0]);
            case 2: {
                if (unsorted[0] > unsorted[1]) {return new SimpleImmutableEntry<>(unsorted[1], unsorted[0]);}
                else return new SimpleImmutableEntry<>(unsorted[0], unsorted[1]);
            }
            default:{
                int min = unsorted[0];int max = unsorted[1];
                
            }
        }

    }
}
