/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  Percolation system using an n-by-n grid of sites.
 *
 *  Written:       8/05/2019
 *
 *  % javac-algs4 Percolation.java
 *  % java-algs4 Percolation <file>
 *
 *  Formatter: 
 *      <file> : 
 *          n -> number of lines
 *          row col -> list of rows and cols
 *          Ex.:
 *               20
 *                 7  11
 *                18  11
 *                12   5
 *                 9   5
 *                 5   9
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class Percolation {

    private final WeightedQuickUnionUF wqu;
    private final int nt; // Number of elements
    private int os; // Number of open sites
    private boolean[] op; // Is Open?
    private boolean p; // Is Percolate?
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("N must be gran than 0!");

        wqu = new WeightedQuickUnionUF((n * n) + (n * 2));
        op  = new boolean[n * n];
        os  = 0;
        nt  = n;
        p = false;


    }                

    // open site (row, col) if it is not open already
    public void open(int row, int col) {

        this.validateRowCol(row, col);

        int idx = getIndex(row, col);
        int idxWqu = getIndexWqu(row, col);

        if (!op[idx]) {

            os += 1;
            op[idx] = true;

            if (row == 1)
                wqu.union(row - 1, idxWqu);

            if (row == nt) {
                wqu.union(row - 1, idxWqu);
                ;
            }

            wqu.union(col - 1 + nt, idxWqu);
            if (col > 1 && op[idx - 1])
                wqu.union(idxWqu, idxWqu - 1);
            if (col < nt && op[idx + 1])
                wqu.union(idxWqu, idxWqu + 1);
            if (row > 1 && op[idx - nt])
                wqu.union(idxWqu, idxWqu - nt);
            if (row < nt && op[idx + nt])
                wqu.union(idxWqu, idxWqu + nt);

        }

    } 

    
    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.validateRowCol(row, col);
        return op[getIndex(row, col)];
    }      

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        this.validateRowCol(row, col);
        return wqu.find(getIndexWqu(row, col)) == wqu.find(0);
    }      

    // number of open sites
    public int numberOfOpenSites() {
        return os;
    }   

    // does the system percolate?    
    public boolean percolates() {
        return wqu.connected(0, nt - 1);
    } 

    // validate row and col
    private void validateRowCol(int row, int col) {
        if (row <= 0 || row > nt)
            throw new IllegalArgumentException("row value " + row + " out of bounds!");
        if (col <= 0 || col > nt)
            throw new IllegalArgumentException("col value " + col + " out of bounds!");
    }

    // get row index
    private int getIndex(int row, int col) {
        return (nt * (row - 1)) + col - 1;
    }

    // get row index
    private int getIndexWqu(int row, int col) {
        return (nt * (row - 1)) + col - 1 + (nt * 2);
    }

    // test client (optional)
    public static void main(String[] args) {

        Stopwatch sw = new Stopwatch();
        
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // n-by-n percolation system

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        
        System.out.println(String.format("number of open sites: %s", perc.numberOfOpenSites()));
        System.out.println(String.format("is percolates: %s", perc.percolates()));
        System.out.println(String.format("Running time: %ss", sw.elapsedTime()));

    }    

}