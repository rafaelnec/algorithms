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
	private int S = 0;
	private int N;

	private class Node {
		Item item;
		int next() {
			return getRandom();
		}
	}

	// construct an empty randomized queue	
	public RandomizedQueue() {
		s = (Item[]) new Object[1];
	}    

	// is the randomized queue empty?             
	public boolean isEmpty() {
		return S == 0;
	}                

	// return the number of items on the randomized queue
	public int size() {
		return S;
	}              

	// add the item         
	public void enqueue(Item item) {
		if (N == s.length) resize(2 * s.length);
		int iaux = getRandom();
		Item vaux = item;
		if (N > 0){ vaux = s[iaux]; s[iaux] = item; }
		s[N++] = vaux;
        S++;
	}

	// remove and return a random item           
	public Item dequeue() {
		if (N > 0 && N == s.length/4) resize(s.length/2);
		S--;
		return s[--N];
	}                 

	// return a random item (but do not remove it)   
	public Item sample() {
		return s[getRandom()];
	}               

	// return an independent iterator over items in random order      
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}    

	private class ArrayIterator implements Iterator<Item>    { 
        private Item[] si;
        int i = 0;
        public ArrayIterator() {
        	si = (Item[]) new Object[N];
        	int aux = 0;
        	Item vaux = s[0];
        	for (int j = 0; j < N; j++){
        		if (j > 0){
        			aux = getRandom(j);
        			vaux = si[aux];
        		} 
				si[aux] = s[j];
				si[j] = vaux;
        	} 
        }
        public void remove()        { throw new UnsupportedOperationException(); } 
        public boolean hasNext()    { return i < N; }        
        public Item next()          { 
            if (hasNext() == false) throw new NoSuchElementException(); 
            return si[i++]; 
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) 
            copy[i] = s[i];
        s = copy;
    }

    private int getRandom() { return getRandom(N); }

    private int getRandom(int max) {
    	if (max == 0) return 0;
    	else return StdRandom.uniform(max);
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

        deq.dequeue();

        

        System.out.println("-----------------------");
        Iterator<Integer> it = deq.iterator();
        while(it.hasNext()) {
            System.out.print(it.next() + " | ");
        }
        System.out.println();
        it = deq.iterator();
        while(it.hasNext()) {
            System.out.print(it.next() + " | ");
        }
        System.out.println();
        System.out.println("Random: " + deq.sample());
        System.out.println("Is Empty: " + deq.isEmpty());
        System.out.println("Size: " + deq.size());
        System.out.println("-----------------------");

	}   
}