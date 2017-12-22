package hw4.puzzle;
import java.util.Queue;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;

/** Add a searchNode Object will be better, reduce the memory and
 *
 */
public class Solver {

    public class Com implements Comparator<Board>
    {
        @Override
        public int compare(Board o1, Board o2) {
            return  (o1.priority-o2.priority);
        }
    }

    private MinPQ<Board> priorityBoard;
    private int totalMove=0;
    private Board initalBoard;
    private Board prev;
    private Board goal;
    Queue<Board> solutionQueue;

    /** Initial the Solver*/
    public Solver(Board intial){
        initalBoard = intial;
        Com comparator = new <Board>Com();
        priorityBoard = new MinPQ<Board>(comparator);
        solutionQueue = new LinkedList<Board>();
        solutionQueue.offer(initalBoard);
        prev=intial;
        goal=intial;
    }

    /** Return the total number of move*/
    public int moves(){
        if(totalMove==0){
            makeSolution(initalBoard);
        }
        return totalMove;
    }
    /** Iterator through the solution */
    public Iterable<Board> solution(){
        if(totalMove==0){
            makeSolution(initalBoard);
        }
        return solutionQueue;
    }

    public void makeSolution(Board next){
        if(next.isGoal()) return;
        for(Board board:hw4.puzzle.BoardUtils.neighbors(next)){
            if (!board.equals(prev)){
                board.updataPriority(prev.getMoves());
                priorityBoard.insert(board);
            }
        }
        Board temp = priorityBoard.delMin();
        prev = next;
        solutionQueue.offer(temp);
        totalMove+=1;
        makeSolution(temp);
    }

    // DO NOT MODIFY MAIN METHOD
    /* Uncomment this method once your Solver and Board classes are ready.*/
    public static void main(String[] args) {

        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution()) {
            StdOut.println(board);
       }
    }

}
