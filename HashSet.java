class Node{
	int data;
	Node next =null;
	Node(int data)
	{
		this.data=data;
		this.next=null;
	}
}

public class HashSet {
	
	Node head,tail;
	
	public HashSet()
	{
		head=tail=null;
	}
	
	public void add(int key)
	{
		Node temp = new Node(key);
		Node p= head;
		while(p!=null)
		{
			if(p.data == key) return;
			p=p.next;
		}
		
		if(head == null)
		{
			head=temp;
			tail=temp;
		}
		else {
			tail.next=temp;
			tail=temp;
		}
	}
	
	public void remove(int key)
	{
		Node p = head, q = null;
        while(p != null && p.data != key){
            q = p;
            p = p.next;
        }
        
        if(p == null)return;
        if(head.data == key){
            head = head.next;
        }else{
            q.next = p.next;
        }
        
        if(p == tail)tail = q;
        p.next = null;
		
	}
	
	public boolean contains(int key)
	{
		 Node p =head;
	        while(p!=null){
	            if(p.data == key)return true;
	            p = p.next;
	        }
	        return false;
	}

}


