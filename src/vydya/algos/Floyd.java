package vydya.algos;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.min;

public class Floyd {
    static int INF = MAX_VALUE;
    public static void main(String[] args) {
        int[][] dist = {
              //  0    1     2     3
                { 0,   4,    INF,  5},  // 0
                { INF, 0,    1,    INF},  // 1
                { 2,   INF,  0,    3},  // 2
                { INF, INF,  1,    0},  // 3
                };

        floydWarshall(dist);
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                String value = dist[i][j] == INF ? "INF " : dist[i][j]+"  ";
                System.out.print(value);
            }
            System.out.println();
        }
    }

    static void floydWarshall(int[][] dist){
        int V = dist.length;
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(dist[i][k] != INF && dist[k][j] != INF)
                        dist[i][j] = min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
