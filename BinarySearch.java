import java.util.Arrays;


public class BinarySearch {
	
	public static void main(String[] args)
	{
		int[] a = new int[]{12,34,56,78,100,120,130,140};
	
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		int res =binarySearch(a);
		System.out.println(res);
	}
	
	public static int binarySearch(int[] a)
	{
		int p=0;
		int r=a.length-1;
		int x=140;
		while(p<=r)
		{
			int q=(p+r)/2;
			if(x<a[q])
			{
				r=q-1;
			}
			else if(x>a[q])
			{
				p=q+1;
			}
			else
			{
				return q;
			}
			
		}
		return -1;
	}

}
