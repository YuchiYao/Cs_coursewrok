import java.util.*;

/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    boolean targetFound;
    int t;
    int source;
    Maze maze;
    Queue bsfList;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {

        super(m);
        maze = m;
        source = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
        targetFound=false;
        bsfList= new LinkedList();
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
        /* Your code here. */
        marked[s]=true;
        announce();
        if (s==t) targetFound=true;
        if (targetFound==true) return;
        for(int edge:maze.adj(s)){
            if(marked[edge]!=true){

                bsfList.offer(edge);
                distTo[edge]=1+distTo[s];
                edgeTo[edge]=s;
                announce();
            }
        }
        while (!bsfList.isEmpty()){
            bfs((int)bsfList.poll());
        }

    }


    @Override
    public void solve() {
         bfs(source);
    }
}

