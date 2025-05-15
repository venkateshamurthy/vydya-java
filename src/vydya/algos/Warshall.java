package vydya.algos;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.min;

public class Warshall {
        public static void main(String[] args) {
            /**
             0 1 0 0
             0 0 1 0
             0 0 0 1
             0 0 0 0
             */
            /*int[][] graph = {
                    {0, 1, 0, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 1},
                    {0, 0, 0, 0}
            };*/
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[][] graph = new int[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    graph[i][j] = sc.nextInt();
                }
            }
            warshall(graph);

        }
        static void warshall(int[][] reach){
            int V = reach.length;
            for (int k = 0; k < V; k++) {
                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        //reach[i][j] = (reach[i][j]!=0 || (reach[i][k]!=0 && reach[k][j]!=0)) ? 1:0;
                        reach[i][j] = 0x01 & (reach[i][j] | (reach[i][k] & reach[k][j]));
                    }
                }
            }
            for (int[] ints : reach) {
                System.out.println(Arrays.toString(ints));
            }
        }

}
