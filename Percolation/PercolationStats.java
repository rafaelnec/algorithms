/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  To estimate the percolation threshold.
 *
 *  Written:       8/05/2019
 *
 *  % javac-algs4 PercolationStats.java
 *  % java-algs4 PercolationStats 200 100
 *
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom; 
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private static final double CONFIDENCE_INDEX = 1.96;

    private final int t; // Trials
    private final double[] x;
    private double mean;
    private double stddev;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be gran than 0!");

        t = trials;
        x = new double[trials];
        mean = Double.NaN;
        stddev = Double.NaN;

        double nn = n * n; 

        for (int i = 0; i < trials; i++) {

            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                int rone = StdRandom.uniform(1, n + 1);
                int rtwo = StdRandom.uniform(1, n + 1);
                if (p.isOpen(rone, rtwo))
                    continue;
                p.open(rone, rtwo);
            }

            double op = p.numberOfOpenSites();
            x[i] = op / nn;
            
        }

    }

    // sample mean of percolation threshold  
    public double mean() {
        if (Double.isNaN(mean))
            mean = StdStats.mean(x);
        return mean;
    }              

    // sample standard deviation of percolation threshold      
    public double stddev() {
        if (Double.isNaN(stddev))
            stddev = StdStats.stddev(x);
        return stddev;
    }       

    // low  endpoint of 95% confidence interval                 
    public double confidenceLo() {
        return mean() - (PercolationStats.CONFIDENCE_INDEX * (stddev() / Math.sqrt(t)));
    }         

    // high endpoint of 95% confidence interval        
    public double confidenceHi() {
        return mean() + (PercolationStats.CONFIDENCE_INDEX * (stddev() / Math.sqrt(t)));
    }        

    // test client (described below)
    public static void main(String[] args) {

        Stopwatch sw = new Stopwatch();
        
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);

        System.out.println(String.format("mean: %s", ps.mean()));
        System.out.println(String.format("stddev: %s", ps.stddev()));
        System.out.println(String.format("95%% confidence interval: [%s,%s]", 
            ps.confidenceLo(), ps.confidenceHi()));
        System.out.println(String.format("Running time: %ss", sw.elapsedTime()));

    }   

}