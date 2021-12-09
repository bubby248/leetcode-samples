package arrayselection;

public class SecondLargestValue {
	
	public static void main(String[] args)
	{
		// Find the second largest max of an array. Example: for input  [5,1,1,5]  output should be 1 ,for input [10,8,9,7,9] output is 9
		int[] a = {10,8,9,7,9};
		int max=0;
		int secondmax=0;
		for(int i=0;i<a.length;i++)
		{
			if(a[i]>max)
			{
				secondmax=max;
				max=a[i];
				
			}
			else if(a[i]>secondmax && a[i] != max)
			{
				secondmax=a[i];
			}
		}
		
		System.out.println(secondmax);
	}

}
