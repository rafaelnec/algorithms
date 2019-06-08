/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  Stack algorithm Object Implementation.
 *
 *  Written:       5/06/2019
 *
 *  % javac Stack.java
 *  % java Stack
 *  Proposition.  A stack with N items uses~ 40 N bytes.
 *
 **************************************************************************** */
public class Stack<Item>
{

    private Node first = null;

    private class Node
    {
        Item item;
        Node next;
    }   

    public boolean isEmpty()
    {
        return first == null;
    }   

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }   

    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        return item;
    }
    
}
