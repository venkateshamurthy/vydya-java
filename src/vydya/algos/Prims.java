package vydya.algos;

import java.util.Arrays;
import java.util.Scanner;

public class Prims {

    static void primMST(int[][] a, int n, int source) {
        int[] distance = new int[n];        // Minimum weight to include a vertex
        int[] parent = new int[n];          // To store MST edges
        boolean[] visited = new boolean[n]; // Tracks included vertices

        // Initialize all distance to max value and parent to -1
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // Start from the source vertex
        distance[source] = 0;

        // MST will have n vertices
        for (int count = 0; count < n - 1; count++) {
            int u = minIndex(distance, visited);
            visited[u] = true;

            // Update distances and parent for adjacent vertices
            for (int v = 0; v < n; v++) {
                if (a[u][v] != 0 && !visited[v] && a[u][v] < distance[v]) {
                    distance[v] = a[u][v];
                    parent[v] = u;
                }
            }
        }

        // Print the MST and total cost
        printMST(parent, a);
    }

    static int minIndex(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minimumIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minimumIndex = v;
            }
        }
        return minimumIndex;
    }

    static void printMST(int[] parent, int[][] graph) {
        int totalCost = 0;
        System.out.println("Edge \tWeight");
        for (int i = 0; i < graph.length; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
                totalCost += graph[i][parent[i]];
            }
        }
        System.out.println("Total cost of MST = " + totalCost);
    }

    public static void main(String[] args) {
        /* result = 6
0 3 999 5
3 0 1 999
999 1 0 2
5 999 2 0
         */
        Scanner scannerobj = new Scanner(System.in);
        System.out.println("\nEnter the number of nodes:");
        int n = scannerobj.nextInt();
        int[][] a = new int[n][n];

        System.out.println("\nEnter the cost adjacency matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = scannerobj.nextInt();

        System.out.println("\nEnter the source node:");

        int source = scannerobj.nextInt();
        Arrays.stream(a).map(Arrays::toString).forEach(System.out::println);
        //System.out.println("Matrix:"+ Arrays.toString(a));
        primMST(a, n, source);
    }
    /*
0 2 0 6 0
2 0 3 8 5
0 3 0 0 7
6 8 0 0 9
0 5 7 9 0
     */
}