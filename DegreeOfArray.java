package arrayselection;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

//Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
//
//Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

public class DegreeOfArray {
	
	public static void main(String[] args)
	{
		
		System.out.println((char)(4+'a'));// a value is 97
		int[] input = {1,2,2,3,1};
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		for(int i=0;i<input.length;i++)
		{
			if(hm.containsKey(input[i]))
			{
				int count = hm.get(input[i]);
				hm.put(input[i],++count);
			}
			else
			{
			hm.put(input[i], 1);
			}
		}
		// Get the max value from hashmap
		
		int max=(Collections.max(hm.values()));
		
		
		//int max =Collections.max(hm.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getValue();
        System.out.println(hm);
		System.out.println(max);
        
        HashSet<Integer> set = new HashSet<>(); //tracks values which have freq == degree
        HashMap<Integer, Integer> cw = new HashMap<>(); //current window
        
        int res = Integer.MAX_VALUE;
        
        //use sliding window to find shortest window
        int l = 0;
        for(int r = 0; r < input.length; r++) {
            
            //invite element RHS
            cw.put(input[r], cw.getOrDefault(input[r],0)+1);
            if(cw.get(input[r]) == max) set.add(input[r]);
      
            
            //while valid
            while(!set.isEmpty()) {
            	System.out.println(set);
                //process valid
                int len = r - l + 1;
                
                res = Math.min(res, len);
                
                //shorten
                cw.put(input[l], cw.get(input[l])-1);
                if(set.contains(input[l]) && cw.get(input[l]) < max) set.remove(input[l]);
                
                l++;
            }
        }
       
        System.out.println(res);

	}

}
