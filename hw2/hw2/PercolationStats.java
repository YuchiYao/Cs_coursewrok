package hw2;                       
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double x[];
    public PercolationStats(int N, int T) {  // perform T independent experiments on an N-by-N grid
        x = new double [T];
        if(N<=0 || T<=0)
            throw new java.lang.IllegalArgumentException("The size"+N +"and T" +T +"is non-negative");
        int row, col;
        for (int i=0; i<T;i++) {
            Percolation a = new Percolation(N);

            while (a.percolates() == false) {
                row = StdRandom.uniform(0,N);
                col = StdRandom.uniform(0,N);
                a.open(row,col);
            }

            x[i]= a.numberOfOpenSites()/(double)(N*N);
        }

    }
    public double mean() {                   // sample mean of percolation threshold
        return StdStats.mean(x);
    }
    public double stddev() {                  // sample standard deviation of percolation threshold
        double stdDeviation = 0,mean;
        mean=mean();
        for(int i=0;i<x.length;i++){
            stdDeviation+=Math.pow(x[i]-mean,2);
        }
        return Math.sqrt(stdDeviation/(x.length-1));
    }
    public double confidenceLow() {          // low  endpoint of 95% confidence interval
        return mean()-1.96*stddev()/Math.sqrt(x.length);
    }
    public double confidenceHigh() {         // high endpoint of 95% confidence interval
        return mean()+1.96*stddev()/Math.sqrt(x.length);
    }
    public static void main(String[] args){                 // unit testing (not required)
        PercolationStats you =new PercolationStats(20,30 );
        you.mean();
        you.mean();
    }
}                       
