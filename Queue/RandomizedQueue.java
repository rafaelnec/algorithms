/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  RandomizedQueue algorithm.
 *
 *  Written:       5/06/2019
 *
 *  % javac RandomizedQueue.java
 *  % java RandomizedQueue
 *
 **************************************************************************** */
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int size = 0;

    // construct an empty randomized queue  
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
    }    

    // is the randomized queue empty?             
    public boolean isEmpty() {
        return size == 0;
    }                

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }              

    // add the item         
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == s.length) resize(2 * s.length);
        s[size++] = item;
    }

    // remove and return a random item           
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        if (size == s.length/4) resize(s.length/2);
        Item item;
        int index;
        if (size > 0) {
            int i = StdRandom.uniform(size);
            index = --size;
            item = s[i];
            s[i] = s[index];
        } else {
            index = --size;
            item = s[index];
        }
        s[index] = null;
        return item;
    }                 

    // return a random item (but do not remove it)   
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        return s[StdRandom.uniform(size)];
    }               

    // return an independent iterator over items in random order      
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }    

    private class ArrayIterator implements Iterator<Item> { 
        private int i = 0;
        private final Item[] si;
        public ArrayIterator() { 
            si = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) 
                si[i] = s[i];
            StdRandom.shuffle(si, 0, size);
        }
        public void remove()        { throw new UnsupportedOperationException(); } 
        public boolean hasNext()    { return i < size; }        
        public Item next()          { 
            if (!hasNext()) throw new NoSuchElementException(); 
            return si[i++]; 
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) 
            copy[i] = s[i];
        s = copy;
    }

    // unit testing (optional)
    public static void main(String[] args) {

        RandomizedQueue<Integer> deq = new RandomizedQueue<>();
        deq.enqueue(13);
        deq.enqueue(23);
        deq.enqueue(34);
        deq.enqueue(35);
        deq.enqueue(42);
        deq.enqueue(41);
        deq.enqueue(67);
        deq.enqueue(89);
        deq.enqueue(900);
        deq.enqueue(-122);
        deq.enqueue(334);
        deq.enqueue(235);

        deq.enqueue(0);
        deq.enqueue(1);
        deq.dequeue();

        

        System.out.println("-----------------------");
        Iterator<Integer> it = deq.iterator();
        Iterator<Integer> it2 = deq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " | ");
        }
        System.out.println();
        
        while (it2.hasNext()) {
            System.out.print(it2.next() + " | ");
        }
        System.out.println();
        System.out.println("Random: " + deq.sample());
        System.out.println("Is Empty: " + deq.isEmpty());
        System.out.println("Size: " + deq.size());
        System.out.println("-----------------------");

    }   
}