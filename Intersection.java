import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Intersection {
	
			
    public static void main(String[] args)
    {
		int[] nums1 = new int[]{4,9,5};
		int[] nums2 = new int[]{9,4,9,8,4};
	
				
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		HashMap<Integer,Integer> hmap = new HashMap<Integer, Integer>();
		
		 for(int i=0;i<nums1.length;i++)
		 {
			 if(hmap.containsKey(nums1[i]))
			 {
				 hmap.put(nums1[i],hmap.get(nums1[i])+1);
			 }
			 else
			 {
				 hmap.put(nums1[i], 1);
			 }
			
		 }

		 for(int i=0;i<nums2.length;i++)
		 {
			 if(hmap.containsKey(nums2[i]) && hmap.get(nums2[i])>0)
			 {
				 al.add(nums2[i]);
				 int freq = hmap.get(nums2[i]);
				 freq--;
				 hmap.put(nums2[i], freq);
				 
			 }
 
			
		 }
		 
		 System.out.println(Arrays.toString(al.toArray()));
		 System.out.println(hmap);
		
    }

}
