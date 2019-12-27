import java.util.Scanner;

// Assumes that there is only one copy of each item, O(kn) where k is # of items and n is max capacity
class Knapsack {

    // Recursive version
    static int recursiveKnapsack(int[] weights, int[] values, int capacity){
        return(rknapsackIter(weights, values, capacity, weights.length - 1, 0));
    }

    // Helper function for recursive version
    static int rknapsackIter(int[] weights, int[] values, int capacity, int itemsLeft, int value){
        if (itemsLeft == 0 || capacity == 0){
            return value;
        } else if (weights[itemsLeft] > capacity){
            return rknapsackIter(weights, values, capacity, itemsLeft - 1, value);
        } else {
            int take = rknapsackIter(weights, values, capacity - weights[itemsLeft], itemsLeft - 1, value + values[itemsLeft]);
            int leave = rknapsackIter(weights, values, capacity, itemsLeft - 1, value);
            return Math.max(take, leave);
        }
    }

    public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        int items = sc.nextInt();
        int[] weights = new int[items + 1];
        int[] values = new int[items + 1];

        // Value input first, then weight
        for (int i = 1; i < weights.length; i++){
            values[i] = sc.nextInt();
            weights[i] = sc.nextInt();
        }

        int capacity = sc.nextInt();

        System.out.println(recursiveKnapsack(weights, values, capacity));
        sc.close();
    }
}