/*  Name:    Rafael Neves Moraes
 *
 *  Description:  Queue with Stack algorithm.
 *
 *  Written:       5/06/2019
 *
 *  % javac QueueWithStack.java
 *  % java QueueWithStack
 *
 **************************************************************************** */
public class QueueWithStack<Item>
{
	private Stack<Item> stackPush = null;
	private Stack<Item> stackPop = null; 

	public QueueWithStack () {
		stackPush = new Stack<Item>();
		stackPop = new Stack<Item>();
	} 

    public boolean isEmpty()
    {
        return stackPush.isEmpty() && stackPop.isEmpty();
    }    

    public void enqueue(Item item)
    {
        stackPush.push(item);
    }   

    public Item dequeue()
    {	
        if (stackPop.isEmpty())
	        while (!stackPush.isEmpty())
	        	stackPop.push(stackPush.pop());

	    return stackPop.pop();
    }

    public static void main (String args[]) {

    	QueueWithStack<Integer> queue = new QueueWithStack<Integer>();
    	queue.enqueue(3);
    	queue.enqueue(5);
    	queue.enqueue(10);
    	queue.enqueue(1);
    	queue.enqueue(4);
    	queue.enqueue(8);
    	queue.enqueue(20);

    	System.out.println(queue.dequeue());
    	System.out.println(queue.dequeue());
    	System.out.println(queue.dequeue());
    	System.out.println(queue.dequeue());
    	System.out.println(queue.dequeue());
    	System.out.println(queue.dequeue());
    	System.out.println(queue.dequeue());

    	System.out.println("-------------------");

    }
}