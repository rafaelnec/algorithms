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

    Node F;
    Node L;   
    int S = 0;
    
    class Node {
        Item item = null;
        Node next = null;
        Node before = null;
    }
    
    // construct an empty deque
    public Deque() {}                           

    // is the deque empty?
    public boolean isEmpty() {
        return S == 0;
    }               

    // return the number of items on the deque
    public int size() {
        return S;
    }             

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (F != null && L == null) { L = F; F = null; }
        addFirstItem(item);
    }          

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (L != null && F == null) { F = L; L = null; }
        addLastItem(item);
    }         

    // remove and return the item from the front
    public Item removeFirst() {
        if (S == 0) throw new NoSuchElementException();
        if (F == L) L = null;
        Item item = F.item;
        F = F.next;
        if (F != null) F.before = null;
        S--;
        return item;
    }  

    // remove and return the item from the end
    public Item removeLast() {
        if (S == 0) throw new NoSuchElementException();
        if (L == F) F = null;
        Item item = L.item;
        L = L.before;
        if (L != null) L.next = null;
        S--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }     

    private class ArrayIterator implements Iterator<Item>    { 
        Node N = F;
        public void remove()        { throw new UnsupportedOperationException(); } 
        public boolean hasNext()    { return N != null; }        
        public Item next()          { 
            if (hasNext() == false) throw new NoSuchElementException(); 
            Item item = N.item; 
            N = N.next; 
            return item; 
        }
    }

    private void addFirstItem(Item item) {
        Node OF = F;
        if (L != null && OF == null) OF = L;
        F = new Node();
        F.next = OF;
        F.item = item;
        if (OF != null) OF.before = F;
        S++;
    }

    private void addLastItem(Item item) {
        Node OL = L;
        if (F != null && OL == null) OL = F;
        L = new Node();
        L.before = OL;
        L.item = item;
        if (OL != null) OL.next = L;
        S++;
    }

    // unit testing (optional)
    public static void main(String[] args) {

        Deque<Integer> deq = new Deque<>();

        deq.addLast(23);
        deq.addFirst(13);
        deq.addLast(36);
        deq.addFirst(1);
        deq.addFirst(456);
        deq.addLast(8787);
        deq.addFirst(789);
        deq.addLast(900);
        deq.addFirst(987);
        deq.addFirst(653);

        // deq.removeLast();
        // deq.removeLast();
        // deq.removeLast();
        // deq.removeLast();
        // deq.removeLast();
        // deq.removeFirst();
        // deq.removeFirst();
        // deq.removeLast();
        // deq.removeLast();
        // deq.removeFirst();

        // deq.removeFirst();
        // deq.addFirst(null);

        Iterator<Integer> it = deq.iterator();
        System.out.println("-----------------------");
        while(it.hasNext()) {
            System.out.print(it.next() + " | ");
        }
        System.out.println();
        System.out.println("Is Empty: " + deq.isEmpty());
        System.out.println("Size: " + deq.size());
        System.out.println("-----------------------");

    }  

}