import java.util.Arrays;


public class ProductArray {
	public static void main(String[] args)
	{
		
	//	Given input is [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
	//	3024 2520 2160 1890 1680 
		int[] a = {1,2,3,4,5};
		int n= a.length;
		int prod[] = new int[n]; 
		int i;
		int temp=1;
		
		for(int j=0;j<n;j++) 
            prod[j]=1; 
		for (i = 0; i < n; i++)  
        { 
            prod[i] = temp; 
            temp *= a[i]; 
        } 
		System.out.println("++++++prod array++++"+Arrays.toString(prod));

		temp = 1; 
		 for (i = n - 1; i >= 0; i--)  
	        { 
	            prod[i] *= temp; 
	            temp *= a[i]; 
	            System.out.println(temp+"+++++"+prod[i]+"++++++++"+a[i]);
	        } 
			
			System.out.println("++++++prod array++++"+Arrays.toString(prod));
	
//		   for (i = 0; i < n; i++) 
//	            System.out.print(prod[i] + " "); 
		   
	/********************************************************************************/	   
		   
			int[] input = {1,2,3,4,5};
			
			int product=1;
			for(int j=0;j<input.length;j++)
			{
				product=product*input[j];
			}
		//	System.out.println(product);
			
			for(int k=0;k<input.length;k++)
			{
				input[k] = product/input[k];
			}
			
			System.out.println("Brute force ARRAY+++"+Arrays.toString(input)+"+++++"+product);

	}

}
