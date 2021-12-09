import java.util.Arrays;


public class InserionSorting {
	
	public static void main(String[] args)
	{
		int[] a = new int[]{100,11,34,54,9,2,3};
		
		int[] res =insertionSort(a);
		
		System.out.println(Arrays.toString(a));
	}
	
	public static int[] insertionSort(int[] a)
	{
		
		for (int i=1;i<a.length;i++)
		{
			int element=a[i];//value to compare
			int j=i-1;//position of last sorted value
			while(j>=0 && a[j]>element)
			{
				System.out.println(Arrays.toString(a));
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=element;
			
		}
		return a;
	}

}
