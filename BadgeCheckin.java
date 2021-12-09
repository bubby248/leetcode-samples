import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// 1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)

// 2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)
public class BadgeCheckin {

	public static void main(String[] args)
	{
		   String badgeRecords2[][] = new String[][] {
				      {"Martha",   "exit"}, 
				      {"Paul",     "enter"}, //1
				      {"Martha",   "enter"},
				      {"Martha",   "exit"},
				      {"Jennifer", "enter"},
				      {"Paul",     "enter"}, //1
				      {"Curtis",   "exit"}, //0
				      {"Curtis",   "enter"}, //1
				      {"Paul",     "exit"}, //0
				      {"Martha",   "enter"},
				      {"Martha",   "exit"},
				      {"Jennifer", "exit"},
				      {"Paul",     "enter"}, //1
				      {"Paul",     "enter"}, //0
				      {"Martha",   "exit"}
				    };

		   
		   Map<String,Integer> map = new HashMap<String, Integer>();

		   for(int i=0;i<badgeRecords2.length;i++)
		   {
			    String key = badgeRecords2[i][0];
				String value =badgeRecords2[i][1];
				
				if(map.get(key)==null && value.equals("enter"))
				{
					map.put(key, 1);
				}
				else if(map.get(key)!=null && value.equals("enter"))
				{
					int count =map.get(key);
					map.put(key, count+1);
				}
				else if(map.get(key)!=null && value.equals("exit"))
				{
					int count =map.get(key);
					map.put(key, count-1);
				}
 
			   }

		   
		   Map<String, Integer> collect = map.entrySet().stream()
					.filter(x -> x.getValue() >= 1)
					.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
		   System.out.println(collect.keySet());
		   System.out.println(collect.values());

	
	}
}
