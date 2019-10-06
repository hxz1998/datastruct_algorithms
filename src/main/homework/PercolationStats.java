import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private final double[] results;
    private final int experimentTimes;
    private final double CONSTANT_NUM = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Illegal args!");
        }
        experimentTimes = trials;
        results = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int step = 0;
            while (true) {
                int posX, posY;
                do {
                    posX = StdRandom.uniform(n) + 1;
                    posY = StdRandom.uniform(n) + 1;
                } while (percolation.isOpen(posX, posY));
                percolation.open(posX, posY);
                results[i] += results[i];
                step += 1;
                if (percolation.percolates()) break;
            }
            results[i] = (double) step / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mu = mean();
        double s = stddev();
        return mu - CONSTANT_NUM * s / Math.sqrt(experimentTimes);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mu = mean();
        double s = stddev();
        return mu + CONSTANT_NUM * s / Math.sqrt(experimentTimes);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println(percolationStats.mean() + "\n" + percolationStats.stddev() + "\n" + percolationStats.confidenceLo());
    }
}
