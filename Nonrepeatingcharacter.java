import java.util.Arrays;
import java.util.HashMap;


public class Nonrepeatingcharacter {
	
	
	
	
	public static void main(String[] args)
	{
		String str = "geeksforgeeks"; 
		int count=1;
		
		HashMap<Character, CountIndex> hm = new HashMap<Character, CountIndex>();
		
		for(int i=0;i<str.length();i++)
		{
			
			if(hm.containsKey(str.charAt(i)))
			{
				//Get Count and increment
				hm.get(str.charAt(i)).incCount();
				//hm.put(str.charAt(i), existingCount++);
			}
			else
			{
				// Put Count index
				hm.put(str.charAt(i), new CountIndex(i));
			}
		}
		
		int result = Integer.MAX_VALUE;
		System.out.println(result);
		System.out.println(Arrays.asList(hm));
		
		for (int j = 0; j < str.length();  j++) 
        { 
             // If this character occurs only once and appears 
            // before the current result, then update the result 
			//System.out.println("RESULT++++"+result);
		//	System.out.println("INDEX++++"+hm.get(str.charAt(j)).index);
		//	System.out.println("COUNT++++"+hm.get(str.charAt(j)).count);
            if (hm.get(str.charAt(j)).count == 1 && result > hm.get(str.charAt(j)).index){ 
                 result = hm.get(str.charAt(j)).index; 
            } 
                
        }  
		System.out.println(result+"++++++"+str.charAt(result));
	}

}

class CountIndex 
{ 
    int count,index; 
      
    // constructor for first occurrence 
    public CountIndex(int index) { 
        this.count = 1; 
        this.index = index; 
    } 
      
    // method for updating count 
    public void incCount() { 
        this.count++; 
    } 
} 
