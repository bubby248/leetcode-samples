
public class CharCount {
	public static void main(String[] args)
	{
		// Given input string "XXAAAABBBCCDAA" print output as "2X4A3B2C1D2A".
		String input ="AAAABBBCCDAA";

        int n = input.length(); 
		 for (int i=0;i<n;i++)
		 {
			  // Count occurrences of current character 
	            int count = 1; 
	            while (i < n - 1 &&  input.charAt(i) == input.charAt(i + 1)) { 
	                count++; 
	                i++; 
	            } 
	            // Print character and its count 
	            System.out.print(count);
	            System.out.print(input.charAt(i)); 
	             
					 
		 }
	}

}
