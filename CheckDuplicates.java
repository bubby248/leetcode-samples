import java.util.HashSet;

public class CheckDuplicate {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4};
        System.out.println("Contains duplicate? " + isDuplicate(input));

        int[] input2 = {1, 2, 3, 2};
        System.out.println("Contains duplicate? " + isDuplicate(input2));
    }

    public static boolean isDuplicate(int[] input) {
        HashSet<Integer> hs = new HashSet<>();

        for (int i = 0; i < input.length; i++) {
            if (hs.contains(input[i])) {
                return true;
            }
            hs.add(input[i]);
        }

        return false;
    }
}
