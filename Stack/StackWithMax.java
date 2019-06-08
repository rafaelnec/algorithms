/*  Name:    Rafael Neves Moraes
 *
 *  Description:  Stack algorithm Object Implementation.
 *
 *  Written:       5/06/2019
 *
 *  % javac StackWithMax.java
 *  % java StackWithMax
 *
 **************************************************************************** */
public class StackWithMax
{

	private Node first = null;
	private Node max = null;

    private class Node
    {
        float item;
        Node next;
    }   

    public boolean isEmpty()
    {
        return first == null;
    }   

    public void push(float number)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = number;
        first.next = oldfirst;
        
        pushMax(number);
    }   

    public float pop()
    {
        float item = first.item;
        first = first.next;

        popMax(item);

        return item;
    }

    public float max() {

    	return max.item;

    }

    private void pushMax(float number) {

    	if (max == null || number > max.item) {
    		Node oldMax = max;
	        max = new Node();
	        max.item = number;
	        max.next = oldMax;
    	} else {
    		Node current = max;
	        while (current.next != null && number < current.next.item)
	        	current = current.next;

	        Node nodeItem = new Node();
	        nodeItem.item = number;
	        nodeItem.next = current.next;
	        current.next  = nodeItem;
    	}
        
    }

    private void popMax(float number) {

    	if (max.item == number) {
    		max = max.next;
    	} else {
    		Node current = max;
	        while (current.next != null && number < current.next.item) 
	        	current = current.next;
	        current.next = current.next.next;
    	}
        
    }

    public static void main (String args[]) {

    	StackWithMax stack = new StackWithMax();
    	stack.push(10);
    	stack.push((float) 9.1);
    	stack.push((float) 50.5);
    	stack.push((float) 50.9);
    	stack.push(23);
    	stack.push(0);
    	stack.push(-10);
    	stack.push((float) 28.9);
    	stack.push(-21);
    	stack.push(89);
    	stack.push(1000);
    	stack.push(99);

    	System.out.println(stack.max()); // 1000
    	
    	stack.pop();
		System.out.println(stack.max()); // 1000
    	
    	stack.pop();
    	System.out.println(stack.max()); // 89

    	stack.pop();
    	System.out.println(stack.max()); // 50.9

    	stack.pop();
    	System.out.println(stack.max()); // 50.9


    }

}