/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  Deque algorithm Object Implementation.
 *
 *  Written:       5/06/2019
 *
 *  % javac Deque.java
 *  % java Deque
 *
 **************************************************************************** */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;   
    private int size = 0;
    
    private class Node {
        Item item = null;
        Node next = null;
        Node before = null;
    }
    
    // construct an empty deque
    public Deque() { }                           

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }               

    // return the number of items on the deque
    public int size() {
        return size;
    }             

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
         
        Node oldFirst = first;
        first = new Node();
        first.next = oldFirst;
        first.item = item;

        if (oldFirst != null) oldFirst.before = first;
        if (last == null) last = first;
        size++;
    }          

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        
        Node oldLast = last;
        last = new Node();
        last.before = oldLast;
        last.item = item;

        if (oldLast != null) oldLast.next = last;
        if (first == null) first = last; 
        size++;
    }         

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        if (first == last) last = null;
        
        Item item = first.item;
        first = first.next;
        if (first != null) first.before = null;

        size--;
        return item;
    }  

    // remove and return the item from the end
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();
        if (last == first) first = null;

        Item item = last.item;
        last = last.before;
        if (last != null) last.next = null;

        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }     

    private class ArrayIterator implements Iterator<Item>    { 
        Node count = first;
        public void remove()        { throw new UnsupportedOperationException(); } 
        public boolean hasNext()    { return count != null; }        
        public Item next()          { 
            if (!hasNext()) throw new NoSuchElementException(); 
            Item item = count.item; 
            count = count.next; 
            return item; 
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

        Deque<Integer> deq = new Deque<>();

        // deq.addFirst(3);
        // deq.removeLast();

        // deq.removeFirst();

        // deq.removeFirst();
        // deq.addFirst(null);

        Iterator<Integer> it = deq.iterator();
        System.out.println("-----------------------");
        while (it.hasNext()) {
            System.out.print(it.next() + " | ");
        }
        System.out.println();
        System.out.println("Is Empty: " + deq.isEmpty());
        System.out.println("Size: " + deq.size());
        System.out.println("-----------------------");

    }  

}