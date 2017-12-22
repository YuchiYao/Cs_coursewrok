import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

/**
 *  @author Josh Hug
 */

public class MazeCycles extends MazeExplorer {
    /* Inherits public fields: 
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    int source;
    private Maze maze;
    private Queue cycle;
    private boolean isCycle = false;
    public MazeCycles(Maze m) {

        super(m);
        maze = m;
        source = maze.xyTo1D(1, 1);
        distTo[source] = 0;
        edgeTo[source] = source;
        cycle = new LinkedList();
    }

    private void cycle(int s) {
        /* Your code here. */
        marked[s]=true;
        announce();
        for(int edge:maze.adj(s)){
            if(marked[edge]!=true) {

                cycle.offer(edge);
                distTo[edge] = 1 + distTo[s];
                edgeTo[edge] = s;
                announce();
                for(int counter:maze.adj(edge))
                    if(marked[counter]==true&&counter!=s){
                        isCycle =true;
                        return;
                    }
            }

        }
        while (!cycle.isEmpty()){
            cycle((int)cycle.poll());
        }

    }

    @Override
    public void solve() {
        cycle(source);
        if(isCycle==true) System.out.println("There is a cycle inside the maze");
        else System.out.println("There is not any cycle in the maze");
    }
} 

