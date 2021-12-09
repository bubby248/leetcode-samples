
public class GFG {
	
	// linked list node
	
	static class Node
	{
		boolean data;
		Node next;
	};
	
	
	static int decimalValue( Node head)
	{
	    // Initialized result
	    int res = 0;
	 
	    // Traverse linked list
	    while (head != null)
	    {
	        // Multiply result by 2 and add
	        // head's data
	        res = (res << 1) + (head.data?1:0);
	 
	        // Move next
	        head = head.next;
	    }
	    return res;
	}
	
	// function to create a new node
	
	static Node newNode(int data)
	{
		Node temp = new Node();
		temp.data = (data==1? true:false);
		temp.next = null;
		return temp;
	}
	
	public static void main(String[] args)
	{
		
		int x = -4;
	       System.out.println(x>>1);   
	       int y = 4;
	       System.out.println(y>>1);  
	       
		Node head = newNode(1);
		head.next = newNode(0);
		head.next.next = newNode(1);
		head.next.next.next = newNode(1);
	    System.out.print( "Decimal value is "+decimalValue(head));

	  
	    
	}

}
