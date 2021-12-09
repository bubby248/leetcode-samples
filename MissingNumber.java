import java.util.Arrays;

// Missing number
public class MissingNumber {
	
	public static void main(String[] args)
	{
		//For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
		
		//  m-1,m,2,3----n=max
		//n(n+1)/2    min-1,min,0,1,2,3,....max  -m,.....max:     max-(min-1)* (max+min-1+1)/2
		// Sum = total_numbers * ( first / 2 + last / 2 )
		
		int[] a = {-2,-3,-1};

		int min = a[0];
		int max = a[0];
		int total = 0;
		for(int i=0;i<a.length;i++){
		    if(a[i] <= min){
			  min = a[i];
			}
		    if(a[i] >= max){
				 max = a[i];
			}
		    total += a[i];
		}

		System.out.println(total);
		int expectedTotal =  ((max-min)+1) * (min + max) / 2;

		int missingNumber= (expectedTotal==total)? (max+1): (expectedTotal - total);
		
		System.out.println(min +"   "+max+"   missing number "+ missingNumber);
		
		

	}

}
