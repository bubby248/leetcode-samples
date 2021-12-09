import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class LongestSubString {
	
	public static void main(String[] args)
	{
		String s="hhiujkk";
        int n = s.length(), ans = 0;
//        Map<Character, Integer> map = new HashMap<>(); // current index of character
//        // try to extend the range [i, j]
//        for (int j = 0, i = 0; j < n; j++) {
//            if (map.containsKey(s.charAt(j))) {
//                i = Math.max(map.get(s.charAt(j)), i);
//            }
//            ans = Math.max(ans, j - i + 1);
//            map.put(s.charAt(j), j + 1);
//        }
//		System.out.println(ans);
//		
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		
		int substrlen=0;
		for(int i=0,j=0;i<s.length();i++)
		{
			if(map.containsKey(s.charAt(i)))				
			{
				j=Math.max(map.get(s.charAt(i)), j);
			}
			substrlen=Math.max(substrlen, i-j+1);
			map.put(s.charAt(i), i+1); // store the index
		}
		System.out.println(substrlen);
		System.out.println(map);

        // Reverse of a number
		
		int num=123;
		int rev=0;
		while(num!=0)
		{
			int rem =num%10;
//		      if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && rem > 7)) return 0;
//	          if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && rem < -8)) return 0;
			rev=rev*10+rem;
			num=num/10;
			
		}
		System.out.println(rev);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		// Palindome
		
		int y=10;
		int actual=y;
		int rev1=0;
		while(y!=0)
		{
			int rem1 =y%10;
			rev1=rev1*10+rem1;
			y=y/10;
			
		}
		System.out.println(rev1+"++++"+actual);
		if(rev1==actual)
		System.out.println("Palindrome");	
		else
		System.out.println("Not Palindrome");		
		
		
	}

}
