package vydya.algos;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class Prim {
    PriorityQueue<V> Q   = new PriorityQueue<>();
    List<V> vertices     = new ArrayList<>();
    Map<V, List<V>> adjV = new HashMap<>();
    Map<V, List<E>> adjE = new HashMap<>();

    public static void main(String[] args) {
        int graph[][] = new int[][] {
                /*
                { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 }
                 */
                {0, 3, 999, 5},
                {3, 0, 1, 999},
                {999, 1, 0, 2},
                {5, 999, 2, 0}
        };
        /* output
        Edge 	Weight
        0 - 1 	2
        1 - 2 	3
        0 - 3 	6
        1 - 4 	5
        */
        Prim g = new Prim(graph);
        g.prim(0);
        System.out.println(g.vertices);
    }

    public Prim(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            vertices.add(new V(i));

        for (int i = 0; i < matrix.length; i++) {
            V u = vertices.get(i);
            adjV.put(u, new ArrayList<>());
            adjE.put(u, new ArrayList<>());

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    V v = vertices.get(j);
                    adjV.get(u).add(v);
                    adjE.get(u).add(new E(v, matrix[i][j]) );
                }
            }
        }
    }

    void prim(int index){ prim(vertices.get(index)); }

    void prim(V s) {
        s.key = 0;
        Q.addAll(vertices);

        while (!Q.isEmpty()) {
            V u = Q.poll();
            for (V v: adjV.get(u)) {
                if (Q.contains(v) && w(u, v) < v.key){
                    Q.remove(v);
                    v.pi = u;
                    v.key = w(u, v);
                    Q.add(v); //this add re-prioritizes
                }
            }
        }
    }

    int w(V u, V v) {
        List<E> edges = adjE.get(u);
        for (E e : edges) {
            if (e.to.equals(v))
                return e.weight;
        }
        return MAX_VALUE;
    }

    private static class E implements Comparable<E> {
        V to;
        int weight;
        E(V v, int w) { to = v; weight = w; }

        public int compareTo(E o) { return weight - o.weight; }
        public String toString()  {return to+"("+ weight+")";}
    }

    private static class V implements Comparable<V> {
        int id; int key; V pi;
        public V(int id) { this(id, MAX_VALUE); }
        public V(int id, int key) { this.id = id; this.key = key; }

        public boolean equals(Object o) {return (o instanceof V) &&  id == ((V) o).id;}
        public int hashCode() { return Objects.hashCode(id); }

        public int compareTo(V o) {return key - o.key;}
        public String toString() {return pi==null? id+"" : pi.id+"--("+key+")-->"+id;}
    }
}


