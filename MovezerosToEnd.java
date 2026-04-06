import java.util.Arrays;

public class MoveZerosToEnd {
    public static void main(String[] args) {
        int[] inputArray = {0, 1, 0, 3, 12};

        int lastNonZeroFoundAt = 0;

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] != 0) {
                inputArray[lastNonZeroFoundAt] = inputArray[i];
                lastNonZeroFoundAt++;
            }
        }

        for (int i = lastNonZeroFoundAt; i < inputArray.length; i++) {
            inputArray[i] = 0;
        }

        System.out.println("Zeros moved to end: " + Arrays.toString(inputArray));
    }
}
