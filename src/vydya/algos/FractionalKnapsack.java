package vydya.algos;

import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {
    private static class Item {
        double weight, value;
        double ratio;
        Item(double weight, double value) {
            this.weight = weight;
            this.value = value;
            this.ratio = value / weight;
        }
        public String toString() {return "[w=" + weight + ", v=" + value + "]"+"(" + ratio + ")";}
    }

    public static double getMaxValue(Item[] items, double capacity) {
        Arrays.sort(items, (i1, i2) -> Double.compare(i2.ratio, i1.ratio));
        double totalValue = 0;
        for (Item item : items) {
            if (capacity < 0) break;
            if (capacity - item.weight >= 0) {
                capacity   -= item.weight;
                totalValue += item.value;
                System.out.println(item+"-->"+totalValue);
            } else {
                double fractionalWeight = capacity / item.weight;
                Item leftOver = new Item(fractionalWeight, item.value * fractionalWeight);
                capacity     -= leftOver.weight;
                totalValue   += leftOver.value;
                System.out.println("capacity left:"+capacity+", "+leftOver+"-->"+totalValue);
                //break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter capacity:");
        int capacity = Integer.parseInt(in.nextLine());
        System.out.println("Enter weight and value of elements as in (w1 v1 w2 v2 ...): ");
        String line = in.hasNextLine() ? in.nextLine() : "";
        String[] parts = line.split(" ");
        Item[] items = new Item[parts.length/2];
        for (int i = 0; i < items.length; i+=1) {
            items[i] = new Item(Integer.parseInt(parts[i*2]),
                                Integer.parseInt(parts[2*i+1]));
        }
        System.out.println("Max value:"+getMaxValue(items, capacity));
    }
}