import com.sun.tools.hat.internal.util.Comparer;

import java.util.Comparator;
import java.util.Observable;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */

public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Vertex> costQueue;
    class Vertex {
        int index;
        int cost;
        Vertex(int i,int c){
            index =i;
            cost = c;
        }
    }

    public class VertexComparator implements Comparator<Vertex>
    {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            return  (o1.cost-o2.cost);
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        costQueue = new <Vertex>PriorityQueue(5, new VertexComparator());
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int x= maze.toX(v);
        int y= maze.toY(v);
        int targetx = maze.toX(t);
        int targety = maze.toY(t);
        return Math.abs(x - targetx) + Math.abs(y - targety);

    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an astar search from vertex s. */
    private void astar(int s) {
        marked[s]=true;
        announce();
        if (s==t) targetFound=true;
        if (targetFound==true) return;
        for(int edge:maze.adj(s)){
            if(marked[edge]!=true){
                distTo[edge]=1+distTo[s];
                edgeTo[edge]=s;
                costQueue.offer(new Vertex(edge,distTo[edge]+h(edge)));
                announce();
            }
        }
        while (!costQueue.isEmpty()){
            astar(costQueue.poll().index);
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

