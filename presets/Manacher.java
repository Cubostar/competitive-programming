package presets;
import java.util.Scanner;
import java.util.Arrays;

// O(n) time
// Assumes the character '#' can not appear in the given String (uses it as the dummy character)
class Manacher {

    static void printArray(int[] arr){
        System.out.println(" ");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }

    // Naive O(n^2) solution
    static int squared(String s){
        String n = "";

        for (int i = 0; i < s.length(); i++){
            n += "#" + s.charAt(i);
        }
        n += "#";

        int length = n.length();
        int[] lengths = new int[length];
        for (int i = 0; i < lengths.length; i++){
            lengths[i] = 0;
        }
        int j = 1;

        for (int i = 0; i < length; i++){
            j = 1;
            while (i - j > 0 && i + j < length){
                if (n.charAt(i - j) == n.charAt(i + j)){
                    lengths[i] += 1;
                    j++;
                } else {
                    break;
                }
            }
            lengths[i] /= 2;
        }

        int index = 0;
        for (int i = 1; i < lengths.length; i++){
            if (lengths[i] > lengths[index]){
                index = i;
            }
        }

        int m = 0;
        m = (n.charAt(index) == '#') ? 2 * (lengths[index] + 1) : (2 * lengths[index]) + 1;
        return(m);
    }

    static int manacher(String s){
        char[] n = new char[(s.length() * 2)];
        Arrays.fill(n, '#');
        for (int i = 0; i < n.length; i++){
            if (i % 2 == 1){
                n[i] = s.charAt(i / 2);
            }
        }

        int[] lengths = new int[n.length]; // array that holds the radius of each center's palindrome length
        Arrays.fill(lengths, 0);
        int c = 2; // center of the palindrome with the largest r
        int l = 2; // left bound of palindrome about c
        int r = 2; // right bound of palindrome about c
        int i = c; // current center being evaluated
        int mirror = 0; // mirror index of i

        while (r < n.length){
            printArray(lengths);
            mirror = c - (i - c);
            System.out.println("c: " + c + ", r: " + r + ", l: " + l + ", i: "+ i + ", mirror: " + mirror);
            if (c - lengths[mirror] < l){
                lengths[i] = r - i;
            } else {
                lengths[i] = lengths[mirror];
            }

            for (int j = lengths[i] + 1; i - j > 0 && i + j < n.length; j++){
                 if (n[i - j] == n[i + j]){
                     lengths[i]++;
                     if (i + j > r){
                         c = i;
                         l = c - j;
                         r = c + j;
                     }
                 }
            }
            i++;
        }

        for (int j = i + 1; j < n.length; j++){
            lengths[i + j] = lengths[i - j];
        }
 
        int m = -1;
        for (int j = 1; j < n.length; j++){
            if (lengths[j] > m) m = j;
        }

        m = (n[m] == '#') ? m : m + 1;
        return m;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(manacher(s));

        sc.close();
    }
}