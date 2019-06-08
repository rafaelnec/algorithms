/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  Queue algorithm object implementation.
 *
 *  Written:       5/06/2019
 *
 *  % javac LinkedQueueOfStrings.java
 *  % java LinkedQueueOfStrings
 *
 **************************************************************************** */
public class LinkedQueueOfStrings
{
    private Node first, last;

    private class Node
    {
        /* same as in StackOfStrings */
    }   

    public boolean isEmpty()
    {
        return first == null;
    }    

    public void enqueue(String item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
    }   

    public String dequeue()
    {
        String item = first.item;
        first       = first.next;
        if (isEmpty()) last = null;
        return item;
    }
    
} 