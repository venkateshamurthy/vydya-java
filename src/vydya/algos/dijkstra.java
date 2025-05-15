package vydya.algos;

import java.util.Arrays;
import java.util.Scanner;


public class dijkstra {
    static final int INF = 999;
    public static void diji(int[][] a, int source) {
        int       n = a.length;
        boolean[] visited = new boolean[n];
        int[]     d = Arrays.copyOf(a[source],n);

        int   u = -1; //yet to find the current node to start

        visited[source] = true;
        for (int i = 0; i < n; i++) {
            int min = INF;
            for (int j = 0; j < n; j++) {
                if ( ! visited[j] && d[j] < min) {
                    min = d[j];
                    u = j;
                }
            }

            if (u == -1) break; //could not find the current node to start
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if ( ! visited[v] && d[v] > d[u] + a[u][v]) {
                    d[v] = d[u] + a[u][v];
                }
            }
        }

        System.out.println("\nShortest distances from source:\n");
        for (int i = 0; i < n; i++) {
            System.out.println(source + " -> " + i + " = " + d[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*
        Input
        0   2	1	4
	    2	0	999	3
	    1	999	0	5
	    4	3	5	0

	    Output
	    0 -> 0 = 0
        0 -> 1 = 2
        0 -> 2 = 1
        0 -> 3 = 4
         */
        System.out.println("Enter the number of nodes:");
        int n = sc.nextInt();
        int[][] a = new int[n][n];

        System.out.println("Enter the adjacency matrix (use 999 for infinity):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the source node:");
        int source = sc.nextInt();
        diji(a, source);

        sc.close();
    }
}