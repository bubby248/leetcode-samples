/* Java program to search an element in
   sorted and rotated array using
   single pass of Binary Search*/

//1) Find middle point mid = (l + h)/2
//2) If key is present at middle point, return mid.
//3) Else If arr[l..mid] is sorted
//    a) If key to be searched lies in range from arr[l]
//       to arr[mid], recur for arr[l..mid].
//    b) Else recur for arr[mid+1..h]
//4) Else (arr[mid+1..h] must be sorted)
//    a) If key to be searched lies in range from arr[mid+1]
//       to arr[h], recur for arr[mid+1..h].
//    b) Else recur for arr[l..mid]
    		
 
class BinarySearchRotated {
    // Returns index of key in arr[l..h]
    // if key is present, otherwise returns -1
	// arr, 0, n - 1, key
    static int search(int arr[], int l, int h, int key)
    {
        if (l > h)
            return -1;
 
        int mid = (l + h) / 2;
        if (arr[mid] == key)
            return mid;
 
        /* If arr[l...mid] first subarray is sorted */
        if (arr[l] <= arr[mid]) {
            /* As this subarray is sorted, we
               can quickly check if key lies in
               half or other half */
            if (key >= arr[l] && key <= arr[mid])
                return search(arr, l, mid - 1, key);
            /*If key not lies in first half subarray,
           Divide other half  into two subarrays,
           such that we can quickly check if key lies
           in other half */
            return search(arr, mid + 1, h, key);
        }
 
        /* If arr[l..mid] first subarray is not sorted,
           then arr[mid... h] must be sorted subarray*/
        if (key >= arr[mid] && key <= arr[h])
            return search(arr, mid + 1, h, key);
 
        return search(arr, l, mid - 1, key);
    }
 
    // main function
    public static void main(String args[])
    {
        int arr[] = { 4, 7, 6, 5, 8, 9, 1, 2, 3 };
        int n = arr.length;
        int key = 7;
        int i = search(arr, 0, n - 1, key);
        if (i != -1)
            System.out.println("Index: " + i);
        else
            System.out.println("Key not found");
    }
}