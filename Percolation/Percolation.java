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
import edu.princeton.cs.algs4.StdRandom; 
import edu.princeton.cs.algs4.StdStats; 
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class Percolation {

    WeightedQuickUnionUF wqu;
    int s; // Number of open sites
    int[][] f; // Array of points opened
    int t; // Constructor argument n - 1
    boolean p; // Is Percolate?
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n <= 0)
            throw new IllegalArgumentException("N must be gran than 0!");

        wqu = new WeightedQuickUnionUF( (n * n) + (n * 2) );
        s   = 0;
        f   = new int[n][n];
        t   = n - 1;
        p   = false;
    }                

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        this.validateRowCol(row, col);
        wqu.union(getRowIndex(row), getColIndex(row, col));
        s = s + 1;
        f[row - 1][col - 1] = 1;
        setFull(row, col);
    }       

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.validateRowCol(row, col);
        return wqu.connected(getRowIndex(row), getColIndex(row, col));
    }      

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        this.validateRowCol(row, col);
        if(f[row-1][col-1] == 2)
            return true;
        return false;
    }      

    // number of open sites
    public int numberOfOpenSites() {
        return s;
    }   

    // does the system percolate?    
    public boolean percolates() {
        return p;
    } 

    // validate row and col
    private void validateRowCol(int row, int col) {
        if(row <= 0 || row > t + 1)
            throw new IllegalArgumentException("row value " + String.valueOf(row) + " out of bounds!");
        if(col <= 0 || col > t + 1)
            throw new IllegalArgumentException("col value " + String.valueOf(col) + " out of bounds!");
    }

    // get row index
    private int getRowIndex(int row) {
        return row - 1;
    }

    // get col index 
    private int getColIndex(int row, int col) {
        return (t * row) + col - 1;
    }

    // set index was full
    private void setFull(int row, int col) {
        setFull(row, col, false);
    }

    // set inde was full with force fill
    private void setFull(int row, int col, boolean fill) {
        int r = row - 1;
        int c = col - 1;
        if(r >= 0 && r <= t && c >= 0 && c <= t) {
            if( f[r][c] == 1 && ( r == 0 || fill ) ) {
                  f[r][c] = 2;  
                  setFull(row, col + 1, true);
                  setFull(row, col - 1, true);
                  setFull(row + 1, col, true);
                  setFull(row - 1, col, true);
            } 
            if(r > 0 && f[r][c] == 1 && f[r - 1][c] == 2){f[r][c] = 2;  setFull(row - 1, col, true); } 
            if(r < t && f[r][c] == 1 && f[r + 1][c] == 2){f[r][c] = 2;  setFull(row + 1, col, true);} 
            if(c > 0  && f[r][c] == 1 && f[r][c - 1] == 2){f[r][c] = 2;  setFull(row, col - 1, true);} 
            if(c < t  && f[r][c] == 1 && f[r][c + 1] == 2){f[r][c] = 2;  setFull(row - 1, col, true);}

            if(r > 0 && f[r][c] == 2 && f[r - 1][c] == 1){ setFull(row - 1, col, true); } 
            if(r < t && f[r][c] == 2 && f[r + 1][c] == 1){ setFull(row + 1, col, true);} 
            if(c > 0  && f[r][c] == 2 && f[r][c - 1] == 1){ setFull(row, col - 1, true);} 
            if(c < t  && f[r][c] == 2 && f[r][c + 1] == 1){ setFull(row, col + 1, true);} 

            if(r == t && f[r][c] == 2) p = true; 

        }
        
    }             

    // test client (optional)
    public static void main(String[] args) {

        Stopwatch sw = new Stopwatch();
        Runtime rt  = Runtime.getRuntime();
        long rtm = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // n-by-n percolation system

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        
        long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - rtm) /(1024 * 1024);

        System.out.println(String.format("number of open sites: %s", perc.numberOfOpenSites()));
        System.out.println(String.format("is percolates: %s", perc.percolates()));
        System.out.println(String.format("Total memory usaged: %sMB", memory));
        System.out.println(String.format("Running time: %ss",sw.elapsedTime()));

    }    

}