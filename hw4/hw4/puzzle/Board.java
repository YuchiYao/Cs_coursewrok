package hw4.puzzle;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

// 二维数组没有size（）只有length()
public class Board {
    int moves;
    int [][]layoutTiles;
    int priority;
    int [][]goalTiles;
    int size;
    public Board(int[][]tiles){
        size = Math.max(tiles.length,tiles[0].length);
        layoutTiles = new int[size][size];
        for(int i=0;i<size;i++){
            System.arraycopy(tiles[i],0,layoutTiles[i],0,size);
        }

        goalTiles = new int[size][size];
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                goalTiles[i][j]=i*size+j+1;
            }
        }
        goalTiles[size-1][size-1]=0;
        moves =0;
        priority=hamming()+moves;

    }
    /** Read from a file */
    public Board(String filename){
        File file = new File(filename);

    }

    /** The i and j is between 0 and size*/

    public int tileAt(int i,int j){
        if (i<0||i>size||j>size){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return layoutTiles[i][j];
    }

    /**
     *
     * @return size
     */
    public int size(){
        return size;
    }

    /**
     *
     * @return the number of misplaced number
     */
    public int hamming(){
        // Don't need to check the last position;//
        int counter=0;
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (goalTiles[i][j]!=layoutTiles[i][j]){
                    counter+=1;
                }
            }
        }
        return counter-1;
    }
    /** update moves and priority. */
    public void updataPriority(int move){
        moves=move+1;
        priority= hamming()+moves;
    }
    /** return moves*/
    public int getMoves(){
        return moves;
    }
    /**
     *
     * @return the number moves to the goal layout.
     */
    public int manhattan(){
        int counter=0;
        int coldif, rowdif;
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (goalTiles[i][j]!=layoutTiles[i][j]&&layoutTiles[i][j]!=0){
                    rowdif = Math.abs((layoutTiles[i][j]-1)/size-i);
                    coldif = Math.abs((layoutTiles[i][j]-1)%size-j);
                    counter+=rowdif+coldif;
                }
            }
        }
        return counter;
    }


    public boolean isGoal(){
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (goalTiles[i][j]!=layoutTiles[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check the object has the same class or not. and
     * @param y
     * @return
     */
    public boolean equals(Object y){
        Board yBoard= (Board) y;
        for(int i=0;i<size;i++){
            for (int j=0;j<size-1;j++){
                if (goalTiles[i][j]!=yBoard.layoutTiles[i][j]){
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
    public static void main(String[] args) {
        /*int [][] layout = new int [2][2];
        layout [0][0]=1;
        layout [0][1]=2;
        layout [1][0]=0;
        layout [1][1]=3;
        Board map = new Board(layout);
        layout[1][0]=3;
        layout[1][1]=0;
        System.out.println(map.toString());*/
    }

}
