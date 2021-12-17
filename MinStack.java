import java.util.ArrayList;
import java.util.List;

public class MinStack {
	
	List<Integer> list;
	
	public MinStack()
	{
		list = new ArrayList<Integer>();
	}

	public static void main(String[] args)
	{
		
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		minStack.getMin(); // return -3
		minStack.pop();
		minStack.top();    // return 0
		minStack.getMin(); // return -2
	}
	
	public void push(int val) {
        list.add(list.size(),val);
    }
    
    public void pop() {
        list.remove(list.size()-1);
    }
    
    public int top() {
        return list.get(list.size()-1);
    }
    
    public int getMin() {
    	  int min = Integer.MAX_VALUE;
          for(Integer l : list)
          {
              if(l<min)
              {
                  min = l;
              }
          }
          return min;
    	
    }
}
