
public class LargestSumAdjacent {

	
//	Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

//	For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.
	
	public static void main(String[] args)
	{
		
		int[] a ={1, 5, 10, 1, 9};


			        int incl=0;
					int excl=0;
		
		        for(int k : a){
		        	
		        	int temp=incl;	        	
		            incl=Math.max(excl+k, incl);//max(sum till (i-2)+a[i], sum till (i-1))
		            excl=temp;
		        }
		        
		        System.out.println(incl);
		     
//	     int[] b = {5,1,1,5};
//
//	
//	     int first = 0;
//	     int second = 0;
//	     
//	     for(int i=b.length-1;i>=0;i--) {
//	       int new_sum = Math.max(b[i] + second, first);
//	       second = first;
//	       first = new_sum;
//	     }
//	    
//	   System.out.println(first);
	}
			
}
