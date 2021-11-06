package presets;
import java.util.Scanner;

// Assumes that there is only one copy of each item, O(kn) where k is # of items and n is max capacity
class Knapsack {

    // Recursive version, O(2^n)
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

    // Dynamic programming solution
    static int knapsack(int[] weights, int[] values, int capacity){
        // Create memo table: knapsack[i][j] is the maximum value you can get looking at items
        // 1, 2, ..., i and with a capacity of j.
        int[][] knapsack = new int[weights.length][capacity + 1];

        // Set base case: having a capacity of 0 means you can't take any items, so knapsack[i][0] = 0.
        for (int i = 0; i < weights.length; i+= 1){
            knapsack[i][0] = 0;
        }
        // Set base case: having 0 items to choose from means you can't take any items, so knapsack[0][j] = 0.
        for (int j = 0; j < knapsack[0].length; j+= 1){
            knapsack[0][j] = 0;
        }

        // Compute values.
        for (int i = 1; i < weights.length; i+= 1){
            for (int j = 1; j < knapsack[i].length; j+= 1){
                // If the weight of the newest item (ith item) is larger than j, then you can't take it, so the
                // maximum value is knapsack[i - 1][j].
                if (j < weights[i]){
                    knapsack[i][j] = knapsack[i - 1][j];
                // Otherwise, use the Bellman equation.
                } else {
                    knapsack[i][j] = Math.max(knapsack[i - 1][j], knapsack[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        
        return(knapsack[weights.length - 1][capacity]);
    }

    public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
        // I/O is as follows:
        // {number of items} {size of knapsack}
        // {value of item 1} {weight of item 1}
        // {value of item 2} {weight of item 2}
        // ...               ...
        int items = sc.nextInt();
        int capacity = sc.nextInt();
        int[] weights = new int[items + 1];
        int[] values = new int[items + 1];

        // Value input first, then weight
        for (int i = 1; i < weights.length; i++){
            values[i] = sc.nextInt();
            weights[i] = sc.nextInt();
        }

        System.out.println(knapsack(weights, values, capacity));
        sc.close();
    }
}