package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /** Data */
    private boolean[] open;
    WeightedQuickUnionUF uf;
    int openSiteNumber;
    int size;
    public Percolation(int N) {            // create N-by-N grid, with all sites initially blocked
        /** The second last element is the top sentinel and the last element is the bottom sentinel
         * The first row is 0 the last is size -1 */
        if(N <= 0) throw new java.lang.IllegalArgumentException("index " + N + " is not a positive number" );
        size= N;
        openSiteNumber=0;
        uf= new WeightedQuickUnionUF(size*size + 2);
        open = new boolean[size*size + 2];
        for (int i =0; i< open.length; i++) open[i] = false;
        /*for (int j =0; j< size;j++ ){
            uf.union(size*size,xyTo1D(j, 0));
            uf.union(size*size+1,xyTo1D(j, size));
        }*/

    }
    public void open(int row, int col){                     // open the site (row, col) if it is not open already
        int index = xyTo1D(row,col);
        if (open[index]==false){
            open[index]=true;
            openSiteNumber+=1;
            //Union the top sentinel and bottom sentinel
            if(row==0) uf.union(size*size,index);
            if(row==size-1) uf.union(size*size+1,index);

            //Union its neighbour
            if(row-1>=0 && open[xyTo1D(row-1,col)]==true){
                uf.union(xyTo1D(row-1,col),xyTo1D(row,col));
            }
            if(row+1<size && open[xyTo1D(row+1,col)]==true){
                uf.union(xyTo1D(row+1,col),xyTo1D(row,col));
            }
            if(col-1>=0 && open[xyTo1D(row,col-1)]==true){
                uf.union(xyTo1D(row,col-1),xyTo1D(row,col));
            }
            if(col+1<size && open[xyTo1D(row,col+1)]==true){
                uf.union(xyTo1D(row,col+1),xyTo1D(row,col));
            }

        }
    }
    public boolean isOpen(int row, int col){                // is the site (row, col) open?

        return open[xyTo1D(row, col)];
    }
    public boolean isFull(int row, int col){            // is the site (row, col) full?
        return uf.connected(size*size,xyTo1D(row,col));
    }
    public int numberOfOpenSites(){                         // number of open sites
        return openSiteNumber;
    }
    public boolean percolates(){                            // does the system percolate?
        return uf.connected(size*size,size*size+1);
    }

    public int xyTo1D(int row, int col){                    // Change the row and col index to 1D index
        if (row<0||col<0||row>size||col>size) throw new java.lang.IndexOutOfBoundsException("The row" +row +"The colomn"+col+"is out of index");
        return row*size + col;
    }

    public static void main(String[] args){                 // unit testing (not required)
        Percolation a = new Percolation(4);
        a.open(0,0);
        a.open(1,0);
        a.open(0,1);
        a.open(3,0);
        System.out.print(a.percolates());
    }

    /** each element */

}                       
