/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  Permutation algorithm.
 *
 *  Written:       5/06/2019
 *
 *  % javac Permutation.java
 *  % java Permutation
 *
 **************************************************************************** */
import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

   public static void main(String[] args) {

        int count = new Integer(args[0]).intValue();
        RandomizedQueue<String> deq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) 
            deq.enqueue(StdIn.readString());

        Iterator<String> it = deq.iterator();
        for (int i = 0; i < count; i++) 
            System.out.println(it.next());
        
   }

}