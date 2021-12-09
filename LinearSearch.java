
public class LinearSearch {
	public static void main(String[] args)
	{
		int[] a = new int[]{1,11,34,54,9,2,3};
		int x=2;
		int i=0;
        int res =recursiveLS(a,i,x);
        System.out.println(res);
	}
	
	public static int recursiveLS(int[]a,int i,int x)
	{
		if(i>a.length) return -1;
		else if(a[i]==x) return i;
		else return recursiveLS(a,i+1,x);
	}

}
