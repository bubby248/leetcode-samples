import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class DuplicateCharsInString {
	
	public static void main(String[] args)
	{
		// Duplicate charcaters
		String input="Javasjsj";
		char[] charArray = input.toCharArray();
		HashSet<Character> hs = new HashSet<Character>();
		for(int i=0;i<charArray.length;i++)
		{
			if(hs.add(charArray[i])==false)
			{
				System.out.println("Duplicate Element+++"+charArray[i]);
			}
		}
		
		
		// Anagrams:  Each letter should have the same count in both strings. For example, the Army and Mary
		
		String s1 = "Army";
		String s2 ="Mary";
		
		char[] s1charArray = s1.toLowerCase().toCharArray();
		char[] s2charArray = s2.toLowerCase().toCharArray();
		 Arrays.sort(s1charArray);
	     Arrays.sort(s2charArray);
	     boolean result =Arrays.equals(s1charArray, s2charArray);
	     System.out.println(result);
				

	}

}
