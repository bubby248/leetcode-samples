import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] a = {1, 11, 11, 34, 3, 54, 9, 2, 3};

        Arrays.sort(a);
        System.out.println("Sorted with duplicates: " + Arrays.toString(a));

        int result = removeDuplicates(a);

        int[] b = new int[result];
        for (int i = 0; i < result; i++) {
            b[i] = a[i];
        }

        System.out.println("Without duplicates: " + Arrays.toString(b));
        System.out.println("Unique count: " + result);
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
