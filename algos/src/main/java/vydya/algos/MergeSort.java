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
    static final  Random rand = new Random(10L);
    // These fields are DECLARED final and hence MUST BE INITIALIZED in Constructor
    private final int[] data;
    private final int[] temp;
    
    // Constructor
    public MergeSort(int[] input) {
        data = input;
        temp = new int[(1 + input.length) / 2];
    }
    
    
    int[] sort() {
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
        
        // Remember this: You cannot sort if start >= end. so check this condition
        if (start < end) {
            
            //Find the mid point
            int mid = start + (end - start) / 2;

            //Sort both halves
            sort(start,   mid);
            sort(mid + 1, end);

            //Merge both the sorted halves
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
        
        //Copy the 1st half of data to a temp array
        int tLen = mid - start + 1;
        System.arraycopy(data, start, temp, 0, tLen);
        
        //Set indixes
        int k = start;   // Point it to 1st half   (i.e start)
        int i = mid + 1; // Point it to 2nd half   (i.e mid + 1)
        int j = 0;       // Point it to temp array (i.e  0)
        
        // Do the merge
        while(i <= end && j < tLen) data[k++] = // compare-copy 2nd-half and temp
                data[i] < temp[j] ? data[i++] : temp[j++];
        
        //Remember only one of the 2nd half or temp might have left overs
        while(i <= end) data[k++] = data[i++]; // If 2nd half has left over
        while(j < tLen) data[k++] = temp[j++]; // If temp has left over
    }
    
    public static void main(String[] args) {
        System.out.println("\nRuning Merge Sort...");
        
        int[] input;
        if (args.length > 0 && args[0].toLowerCase().startsWith("auto"))
            input = createRandomArray(rand.nextInt(10, 20));
        else
            input = createRandomArray(getSizeFromUser());
        
        int[] result = new MergeSort(input).sort();
        
        System.out.format("Sorted Output :%s\n", Arrays.toString(result));
    }
     
    static int getSizeFromUser() {
        Scanner scanner = new Scanner(System.in) ;
        System.out.print("Enter the size of Integer array:"); 
        return scanner.nextInt();
    }
    
    static int[] createRandomArray(int size) {
        int[] input = new int[size];
        for (int i = 0; i < input.length; i++) input[i] = rand.nextInt(100, 1000);
        System.out.format("Unsorted Input:%s\n" , Arrays.toString(input));
        return input;
    }
    
    void swap(int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}
