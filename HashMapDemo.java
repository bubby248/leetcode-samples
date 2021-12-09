import java.util.concurrent.ConcurrentHashMap;


public class HashMapDemo extends Thread {
	static ConcurrentHashMap<Integer, String> l = new ConcurrentHashMap<Integer, String>();

	public static void main(String[] args) throws InterruptedException
	{
		
		l.put(100,"A"); 
        l.put(101,"B"); 
        l.put(102,"C"); 
        HashMapDemo t=new HashMapDemo(); 
        t.start(); 
        for (Object o : l.entrySet())  
        { 
            Object s=o; 
            System.out.println("s+++++++"+s); 
            Thread.sleep(1000); 
        } 
        //System.out.println(l); 
	}

		public void run()
		{
		    try
	        { 
	            Thread.sleep(1000); 
	            System.out.println(Thread.currentThread().getName());
	            // Child thread trying to add 
	            // new element in the object 
	            l.put(103,"D"); 
	            System.out.println("l++++++++++"+l);
	        } 
	        catch(InterruptedException e) 
	        { 
	            System.out.println("Child Thread going to add element"); 
	        } 		
		 }
		


}
