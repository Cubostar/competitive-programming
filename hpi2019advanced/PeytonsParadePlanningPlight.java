package hpi2019advanced;
import java.util.Scanner;

public class PeytonsParadePlanningPlight {

    // Doesn't work for negative numbers
    static int houseRobber(int[] parade) {
        if (parade.length == 1) {
            return parade[0];
        }

        if (parade.length == 2) {
            return Math.max(parade[0], parade[1]);
        }

        int[] houseRobber = new int[parade.length];
        houseRobber[0] = parade[0];
        houseRobber[1] = Math.max(parade[0], parade[1]);

        for (int i = 2; i < houseRobber.length; i += 1) {
            houseRobber[i] = Math.max(houseRobber[i - 1], houseRobber[i - 2] + parade[i]);
        }

        return houseRobber[houseRobber.length - 1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int floats = sc.nextInt();
        int[] parade = new int[floats];

        for (int i = 0; i < floats; i += 1) {
            parade[i] = sc.nextInt();
        }

        System.out.println(houseRobber(parade));
    }
}
