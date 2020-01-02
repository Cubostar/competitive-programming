package presets;
import java.util.Scanner;

// O(n) time
// Assumes the character '#' can not appear in the given String (uses it as the dummy character)
class Manacher {

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
        String n = "";

        if (s.length() % 2 == 0){
            for (int i = 0; i < s.length(); i++){
                n += "#" + s.charAt(i);
            }
            n += "#";
        } else {
            n = s;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(squared(s));

        sc.close();
    }
}