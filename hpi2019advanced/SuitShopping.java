package hpi2019advanced;
import java.util.Scanner;

public class SuitShopping {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int friends = sc.nextInt();
        int suits = sc.nextInt();
        int cost = 0;
        int[] friendsSizes = new int[friends + 1];
        int suitSize;
        int suitPrice;

        for (int i = 1; i <= friends; i += 1) {
            friendsSizes[i] = sc.nextInt();
        }

        for (int j = 0; j < suits; j += 1) {
            suitSize = sc.nextInt();
            suitPrice = sc.nextInt();

            for (int i = 1; i <= friends; i += 1) {
                if (friendsSizes[i] == suitSize) {
                    cost += suitPrice;
                    friendsSizes[i] = -1;
                }
            }
        }

        System.out.println(cost);
        sc.close();
    }
}
