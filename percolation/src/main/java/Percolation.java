import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final boolean[][] grid;
    private final int size;
    private final WeightedQuickUnionUF uf;
    private int openCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.grid = new boolean[n][n];
        this.size = n * n;
        this.uf = new WeightedQuickUnionUF(size + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        validate(row, col);
        int y = row - 1;
        int x = col - 1;
        if (grid[y][x]) {
            return;
        }
        grid[y][x] = true;
        openCount++;
        int p = y * n + x;
        if (row == 1) {
            uf.union(p, size);
        }
        if (row == n) {
            uf.union(p, size + 1);
        }
        if (row > 1 && grid[y - 1][x]) {
            uf.union(p, (y - 1) * n + x);
        }
        if (row < n && grid[y + 1][x]) {
            uf.union(p, (y + 1) * n + x);
        }
        if (col > 1 && grid[y][x - 1]) {
            uf.union(p, y * n + x - 1);
        }
        if (col < n && grid[y][x + 1]) {
            uf.union(p, y * n + x + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        validate(row, col);
        int y = row - 1;
        int x = col - 1;
        return grid[y][x];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        validate(row, col);
        int y = row - 1;
        int x = col - 1;
        int p = y * n + x;
        return uf.find(size) == uf.find(p);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {

        return uf.find(size) == uf.find(size + 1);
    }

    private void validate(final int row, final int col) {

        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {

        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(2, 3);
        StdOut.println("Grid:\n");
        for (boolean[] row : percolation.grid) {
            for (boolean cell : row) {
                StdOut.print(cell ? "x" : "o");
            }
            StdOut.print("\n");
        }
        StdOut.println("percolates " + percolation.percolates());
        StdOut.println("number of open " + percolation.numberOfOpenSites());
        StdOut.println("is 1,1 open " + percolation.isOpen(1, 1));
        StdOut.println("is 2,3 open " + percolation.isOpen(2, 3));
        StdOut.println("is 3,1 full " + percolation.isFull(3, 1));
        StdOut.println("is 2,3 full " + percolation.isFull(2, 3));
    }
}
