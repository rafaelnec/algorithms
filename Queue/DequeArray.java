/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  Deque algorithm Array Implementation.
 *
 *  Written:       5/06/2019
 *
 *  % javac DequeArray.java
 *  % java DequeArray
 *
 **************************************************************************** */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class DequeArray<Item> implements Iterable<Item> {

    private Item[] s;   
    private int L = 0;
    private int R = 1;
    private int S = 0;
    
    // construct an empty deque
    public DequeArray() {
        s = (Item[]) new Object[2];
    }                           

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
        if (L == -1) resizeLeft(2 * s.length);
        if (L == R) R++;
        s[L--] = item;
        S++;
    }          

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (R == s.length) resizeRight(2 * s.length);
        if (L == R) L--;
        s[R++] = item;
        S++;
    }           

    // remove and return the item from the front
    public Item removeFirst() {
        if (S == 0) throw new NoSuchElementException();
        if (L == R) R--;
        S--;
        s[++L] = null;
        if (L == 3 * s.length/8) resizeLeft(3 * s.length/4);
        return s[L];
    }                

    // remove and return the item from the end
    public Item removeLast() {
        if (S == 0) throw new NoSuchElementException();
        if (L == R) L++;
        S--;
        s[--R] = null;
        if (R == 5 * s.length/8) resizeRight(3 * s.length/4);
        return s[R];
    }                 

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }     

    private class ArrayIterator implements Iterator<Item>    { 
        private int i = L + 1;
        public void remove()        { throw new UnsupportedOperationException(); } 
        public boolean hasNext()    { return i < R; }        
        public Item next()          { 
            if (hasNext() == false) throw new NoSuchElementException(); 
            return s[i++]; 
        }
    }

    private void resizeLeft(int capacity) { resize(capacity, true); }
    private void resizeRight(int capacity) { resize(capacity, false); }
    private void resize(int capacity, boolean isLeft) {
        if (capacity < 2) return;
        Item[] copy = (Item[]) new Object[capacity];
        int I = 0;
        if (isLeft) I = (R+L)/2;
        for (int i = (L+1); i < R; i++) 
            copy[i + I] = s[i];
        s = copy;
        L += I;
        R += I;
    }

    // unit testing (optional)
    public static void main(String[] args) {

        DequeArray<Integer> deq = new DequeArray<>();

        deq.addLast(13);
        deq.addLast(23);
        deq.addFirst(1);
        deq.addFirst(2); 
        deq.addLast(28);
        deq.addFirst(21);
        deq.addFirst(34);
        deq.addLast(47);
        deq.addFirst(-20);
        deq.addLast(15);
        deq.addLast(-22);
        deq.addLast(110);
        deq.addFirst(-24);
        deq.addFirst(-2000);
        deq.addFirst(333);
        deq.addFirst(456);
        deq.addLast(8787);
        deq.addFirst(789);
        deq.addLast(900);
        deq.addFirst(987);
        deq.addFirst(653);

        deq.removeLast();
        deq.removeFirst();
        deq.removeFirst();
        deq.removeLast();
        deq.removeLast();
        deq.removeFirst();
        deq.removeFirst();
        deq.removeFirst();
        deq.removeLast();
        deq.removeLast();
        deq.removeLast();
        deq.removeFirst();
        deq.removeLast();
        // deq.removeFirst();
        // deq.removeLast();
        // deq.removeFirst();
        // deq.removeFirst();
        // deq.removeLast();
        // deq.removeLast();
        // deq.removeLast();
        // deq.removeLast();

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