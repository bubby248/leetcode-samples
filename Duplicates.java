import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Duplicates {
	public static void main(String[] args)
	{
		int[] a = new int[]{1,11,11,34,3,54,9,2,3};
		
		int[] initialArray = new int[]{1,11,11,34,3,54,9,2,3};
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for (int a1 : initialArray)
		{
			al.add(a1);
		}
		
		
	    Set<Integer> set = new HashSet<Integer>(al);
	    
	    System.out.println(set.size());
	    Integer[] output = new Integer[set.size()];
	    set.toArray(output);
	    System.out.println("set++++++++++++++"+set);	
		
		Arrays.sort(a);
	    System.out.println("With Duplicates++++"+Arrays.toString(a));
		int result = removeDuplicates(a);
	    System.out.println("With Duplicates++++"+Arrays.toString(a));
		System.out.println(result);
		int[] b = new int[result];
		for (int i = 0; i < result; i++) {
			b[i]=a[i];
		}
	    System.out.println("Without Duplicates++++"+Arrays.toString(b));
	    
	    
	   int[] input = new int[]{1,2,3,4};
	   System.out.println(isDuplicate(input));
	   
	   // Get the Single number
	   
	   int[] inputArray = new int[]{0,1,0,3,12};
	   
	   int lastNonZeroFoundAt = 0;
	   
	   
	   for(int i=0;i<inputArray.length;i++)
	   {
		   if(inputArray[i]!=0)
		   {
			   inputArray[lastNonZeroFoundAt] = inputArray[i];
			lastNonZeroFoundAt=lastNonZeroFoundAt+1;
			   
		   }		   
	   }
	   for (int i=lastNonZeroFoundAt;i<inputArray.length;i++)
	   {
		   inputArray[i]=0;
	   }

	   System.out.println("zeros last+++++"+Arrays.toString(inputArray));
	   
	   //Plus One
	   


	}
	
	
	public static boolean isDuplicate(int[] input){
		HashSet<Integer> hs = new HashSet<Integer>();
		   for (int i = 0; i < input.length; i++) {
			   if(hs.contains(input[i]))
			   {
				   return true;
		   
			   }
			   hs.add(input[i]);
		   }
		   return false;
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
