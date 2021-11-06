package hpi2019advanced;
import java.util.Scanner;

public class AuFait {
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
