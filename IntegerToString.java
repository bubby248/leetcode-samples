package arrayselection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class IntegerToString {
	static HashMap<Integer, String> map = new HashMap<Integer, String>();

public static void main(String[] args)
{
	constructMap();
	try{
		StringBuilder sb = new StringBuilder();
		
		int input =123;
		if(input>=100)
		{
			int num =input/100;
			sb.append(map.get(num)+" "+"Hundred");
			input =input%100;
		}
		if(input>0)
		{
			if(input<=20)
			{
				sb.append(map.get(input));
			}
			else
			{
				int numTen = input/10;
				sb.append(map.get(numTen*10)+" ");
				int numOne=input%10;
				if(numOne>0){
                    sb.append(" " + map.get(numOne));
                }
			}
		}
		System.out.println(sb.toString());
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

public static void constructMap()
{
	 map.put(0, "Zero");
     map.put(1, "One");
     map.put(2, "Two");
     map.put(3, "Three");
     map.put(4, "Four");
     map.put(5, "Five");
     map.put(6, "Six");
     map.put(7, "Seven");
     map.put(8, "Eight");
     map.put(9, "Nine");
     map.put(10, "Ten");
     map.put(11, "Eleven");
     map.put(12, "Twelve");
     map.put(13, "Thirteen");
     map.put(14, "Fourteen");
     map.put(15, "Fifteen");
     map.put(16, "Sixteen");
     map.put(17, "Seventeen");
     map.put(18, "Eighteen");
     map.put(19, "Nineteen");
     map.put(20, "Twenty");
     map.put(30, "Thirty");
     map.put(40, "Forty");
     map.put(50, "Fifty");
     map.put(60, "Sixty");
     map.put(70, "Seventy");
     map.put(80, "Eighty");
     map.put(90, "Ninety");
	
}
}
