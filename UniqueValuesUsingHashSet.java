import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UniqueElementsUsingSet {
    public static void main(String[] args) {
        int[] initialArray = {1, 11, 11, 34, 3, 54, 9, 2, 3};

        ArrayList<Integer> al = new ArrayList<>();
        for (int value : initialArray) {
            al.add(value);
        }

        Set<Integer> set = new HashSet<>(al);

        System.out.println("Set size: " + set.size());
        System.out.println("Unique elements: " + set);
    }
}
