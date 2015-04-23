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
        private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze; 
    private static final int INFINITY = Integer.MAX_VALUE;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
                super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;   
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {

        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < maze.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            announce();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    announce();
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                    if (targetFound) {
                    return;
                }              
                }
            }
        }
    }




    @Override
    public void solve() {
        bfs(s);
    }
} 

