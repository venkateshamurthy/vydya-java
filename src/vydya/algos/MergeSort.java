/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vydya.algos;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author vydya
 */
public class MergeSort {
    private static final  Random rand = new Random(10L);
    // These fields are DECLARED final and hence MUST BE INITIALIZED in Constructor
    private final int[] data;
    private final int[] temp;
    
    // Constructor
    public MergeSort(int[] input) {
        data = input;
        temp = new int[(1 + input.length) / 2];
    }

    public int[] sort() {
        sort(0, data.length - 1);
        return data;
    }
    
    /**
     * The recursive Sort method which first breaks the array into 2 halves
     * Next both the halves are sorted. After which the merging of 2 halves 
     * happen from where the name of Merge Sort was derived
     * 
     * @param start index of the array 
     * @param end   index of the array
     */
    public void sort(int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            sort(start,   mid);
            sort(mid + 1, end);
            merge(start, mid, end);
        }
    }
    
    /**
     * Strategy
     * First copy off the 1st half from data to the temp.
     * So now the 1st half is freed up. With that compare 2nd half and temp 
     * and copy which ever is lesser to data starting from start. 
     * 
     * Every time the least data holding array's index is incremented.
     * Then only one of temp or 2nd half might be completely drained leaving the
     * other with left overs. So just copy it to the data array
     * 
     * @param start index of the array
     * @param mid   index of the array
     * @param end   index of the array
     */
    void merge(int start, int mid, int end) {
        int tLen = mid - start + 1;
        System.arraycopy(data, start, temp, 0, tLen);
        
        //Set indixes
        int k = start;   // Point it to 1st half   (i.e start)
        int i = mid + 1; // Point it to 2nd half   (i.e mid + 1)
        int j = 0;       // Point it to temp array (i.e  0)
        
        // Do the merge
        while(i <= end && j < tLen) data[k++] = data[i] < temp[j] ? data[i++] : temp[j++];
        while(i <= end)             data[k++] = data[i++]; // If 2nd half has left over
        while(j < tLen)             data[k++] = temp[j++]; // If temp has left over
    }

    private static void sortInput(String inputString) {
        System.out.println("Input:"+inputString);
        int[] input = new int[inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            input[i] = inputString.charAt(i);
        }
        MergeSort mergeSort = new MergeSort(input);
        mergeSort.sort();

        String out = "";
        for (int j : input) {
            out += (char) j;
        }
        System.out.println("Sorted output:"+out);
    }

    private static void sortInput(int[] input) {
        System.out.println("Input:"+Arrays.toString(input));
        MergeSort mergeSort = new MergeSort(input);
        mergeSort.sort();
        System.out.println("Sorted output:"+Arrays.toString(input));
    }
    
    public static void main(String[] args) {
        System.out.println("\nRunning Merge Sort on user defined inputs...");
        sortInput("THANKSGIVING");
        sortInput("UNIVERSITY");
        sortInput(new int[]{29, 10, 14, 37, 13, 5, 25});

        System.out.println("\nRunning Merge Sort on random numbers...");
        Scanner scanner = new Scanner(System.in) ;
        System.out.print("Enter the size of Integer array:");
        int[] input = createRandomArray(scanner.nextInt());
        sortInput(input);
    }
     
    public static int[] createRandomArray(int size) {
        int[] input = new int[size];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        return input;
    }
    
}
