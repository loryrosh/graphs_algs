package graphs.algos.dfs;

import graphs.DiGraph;
import graphs.Vertex;

public class DFS {
    private DiGraph g;
    private Vertex s;

    private boolean[] connected;
    private int counter;

    public DFS(DiGraph g, Vertex s) {
        this.g = g;
        this.s = s;
        connected = new boolean[g.V()];
        dfs(s);
    }

    private void dfs(Vertex v) {
        connected[v.getV()] = true;
        counter++;


    }
}
