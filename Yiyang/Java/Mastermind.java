import java.util.*;

class Mastermind {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] countA = new int[26]; // at most 26 letters
        int[] countB = new int[26];

        int r = 0;
        int s = 0;

        String strA = sc.next();
        String strB = sc.next();
        for (int i = 0; i < N; i++) {
            char chA = strA.charAt(i);
            char chB = strB.charAt(i);

            if (chA == chB) {
                r++;
            } else {
                countA[chA-'A']++;
                countB[chB-'A']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            s += Math.min(countA[i], countB[i]);
        }

        System.out.println(r + " " + s);
    }

}