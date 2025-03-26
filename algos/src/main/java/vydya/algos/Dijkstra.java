package vydya.algos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

class V implements Comparable<V> {
    
    
    final Integer id;
    double d = Double.POSITIVE_INFINITY;
    V u;// parent

    E[] edges;

    private V(Integer id) {
        this.id = id;
    }
    public Integer getId() { return id;}
    public static V of(int id) {return new V(id);}
    
    public String toString() {
        if (u != null) {
            return " " + u.id + "--" + d + "-->" + id + " ";// MEX--1528.0-->KZN 
        } else {
            return id+"";
        }
    }

    public int compareTo(V other) {
        return Double.compare(d, other.d);
    }

}

class E {

    final V v;
    final double w;

    private E(V v, double w) {
        this.v = v;
        this.w = w;
    }

    public static E of(V v, double w) {
        return new E(v, w);
    }

}

public class Dijkstra {
    
    static final Random rand = new Random(10L);
    final PriorityQueue<V> Q = new PriorityQueue<>();
    final Map<Integer, V> map = new HashMap<>();
    final int[][] array;
    
    Dijkstra(int[][] data) {
        this.array = data;
        int size = array.length;
        
        for (int i = 0;i < size; i++) 
            map.put(i, V.of(i));
        
        for (int i = 0;i < size; i++) {
            
            List<E> es = new ArrayList<>();
            
            for(int j = 0; j < size; j++) {
                
                if(array[i][j] != 0) {
                    es.add(E.of(map.get(j), array[i][j]));
                }
            }
            map.get(i).edges = es.toArray(E[]::new);
        }
        
        Arrays.stream(array)
                .map(Arrays::toString)
                .map(s->s.replaceAll("\\[|\\]",""))
                .forEach(System.out::println);
    }
    
    Dijkstra(int size) {
        array = new int[size][size];
        
        for (int i=0;i<size;i++) {
            map.put(i, V.of(i));
            
            for(int j=0;j<size;j++)
                array[i][j] = rand.nextInt(0, 2);
        }
        
        for (int i=0;i<size;i++) {
            List<E> es = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                if(array[i][j] == 1) {
                    array[i][j] = array[j][i] = rand.nextInt(10, 20);
                    es.add(E.of(map.get(j), array[i][j]));
                }
            }
            map.get(i).edges = es.toArray(E[]::new);
        }
        
        Arrays.stream(array)
                .map(Arrays::toString)
                .map(s->s.replaceAll("\\[|\\]",""))
                .forEach(System.out::println);
    }
    
    public Deque<V> computePaths(int S, int T) {
        V s = map.get(S), t= map.get(T);
        
        // Clear the Q
        Q.clear();

        // the start vertex to be zero-distanced and added to Q
        s.d = 0d;
        Q.add(s);

        // Next drain the Q
        while (!Q.isEmpty()) {

            V u = Q.poll();

            // Visit each edge exiting u
            for (E e : u.edges) {

                V v = e.v;

                if (u.d + e.w < v.d) {

                    Q.remove(v);     //First remove the element from Q

                    v.d = u.d + e.w; // reset the distance to the least
                    v.u = u;         // reset the parent to the u.

                    Q.add(v);        // Add it bacl
                }
            }

        }
        
        //Get the shortest path. Deque is the datastructure used to collect
        Deque<V> path = new ArrayDeque<V>();
        
        //Start from the terminal edge and move towards start vertex
        for (V n = t; n != null; n = n.u) path.addFirst(n);
        return path;
    }

    public V vertex(int i) {
        return map.get(i);
    }
    
    /**
     * Resf: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
     * {{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
        { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
        { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
        { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
        { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
        { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
        { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
        { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
        { 0, 0, 2, 0, 0, 0, 6, 7, 0 }}
        * Shortest path from 0 to 8 is 14
     * @param args 
     */

    public static void main(String[] args) {
        
        System.out.println("\n\nComputing Dijkstra....\n");
        
        Dijkstra dijk;
        final int size;
        int start = 0, end = 0;
        
        if (args.length > 0 && args[0].startsWith("auto")) {
            
            size = rand.nextInt(10, 20);
            System.out.println("Assuming a random size(10, 20) of 2D array to build the graph :"+size);
            dijk = new Dijkstra(size);
            end = rand.nextInt(start, size);
        } else {
            // assume an adjaceny matrix of 9x9
            int [][]data = {{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }};
            
            dijk = new Dijkstra(data);
            size = data[0].length;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter start and end vertex within "+(size-1)+":");
            start = scanner.nextInt();
            end = scanner.nextInt();
            dijk = new Dijkstra(size);
            
        }
        
        
        Deque<V> path = dijk.computePaths(start, end); // run Dijkstra
        System.out.println("\nDistance from "+start+" to "+end+" = "+ dijk.vertex(end).d);
        System.out.format("%s\n", path);
        
    }

}
