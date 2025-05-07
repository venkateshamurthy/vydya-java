package vydya.algos;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.min;

public class Floyd {
    static int INF = MAX_VALUE;
    public static void main(String[] args) {
        int[][] dist = {
                { 0, 4, INF, 5, INF },
                { INF, 0, 1, INF, 6 },
                { 2, INF, 0, 3, INF },
                { INF, INF, 1, 0, 2 },
                { 1, INF, INF, 4, 0 } };

        floydWarshall(dist);
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void floydWarshall(int[][] dist){
        int V = dist.length;
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(dist[i][k] != INF && dist[k][j]!= INF)
                        dist[i][j] = min(dist[i][j],dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
