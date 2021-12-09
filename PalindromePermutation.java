import java.util.Arrays;


public class PalindromePermutation {

	public static void main(String[] args)
	{
		String input ="Tact Coa";
		//Check no that more than one character has odd count
		
		int[] table = buildCharFrequencyTable(input);

		
		boolean result=checkMaxOneOdd(table);
		System.out.println(result);
	}
	

	private static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd =false;
		System.out.println(Arrays.toString(table));
		for (int count: table)
		{
			if(count%2 ==1)
			{
				if(foundOdd)
				{
					return false;
				}
				foundOdd=true;
			}
		}
		return true;
	}


	private static int[] buildCharFrequencyTable(String phrase) {
		
		int[] table = new int[Character.getNumericValue('z')-Character.getNumericValue('a')+1];
		for(char c: phrase.toCharArray())
		{
			int x = getCharNumber(c);
			//System.out.println(x+"+++++++++++++"+c);
			if(x!=-1)
			{
				table[x]++;
			}
		}
		System.out.println(Arrays.toString(table));
		return table;
	}

	private static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		if(a<=val && val <=z)
			return val-a;
		return -1;
	}
	
	
}
