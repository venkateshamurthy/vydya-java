package vydya.algos;

import java.util.Arrays;
import java.util.Scanner;

public class dijkstra {
    public static void diji(int a[][],  int source) {
        int   n = a.length;
        boolean[] visited = new boolean[n];
        int[] d = Arrays.copyOf(a[source], n);
        int   u = 0;

        visited[source] = true;

        for (int i = 0; i < n; i++) {
            int min = 999;
            for (int j = 0; j < n; j++) {
                if ( ! visited[j] && d[j] < min) {
                    min = d[j];
                    u = j;
                }
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (! visited[v] && d[v] > d[u] + a[u][v]) {
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