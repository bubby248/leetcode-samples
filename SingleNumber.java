package arrayselection;

public class SingleNumber {
	
	public static void main(String[] args)
	{
		int[] nums ={5, 5, 3, 1,1,2,3};
		
		
			//	 System.out.println(5^3);
				 
//				 101
//				 011
//				 ____
//				 110--- 6
		
		 int a = 0;
	        for (int i : nums) {
	            a ^= i;
	        }
	        
	      System.out.println(a);

		
	}

}
