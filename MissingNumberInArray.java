
public class MissingNumberInArray {
	
	public static void main(String[] args)
	{
		int[] a = new int[]{1,3,4,5};
		
		int arrayLength = 5;
		//n(n+1)/2--5(6/2)
		int sum=0;
		int expectedSum=arrayLength*((arrayLength+1)/2);
		System.out.println(expectedSum);
		for(int i:a)
		{
			sum=sum+i;
		}
			
		System.out.println(expectedSum-sum);
		
	}

}
