package graphs.algos.dfs;

import graphs.DiGraph;
import graphs.Vertex;
import lombok.Getter;

@Getter
public class DFS {
    private DiGraph diGraph;

    private boolean[] connected;
    private int counter;

    public DFS(DiGraph diGraph, Vertex s) {
        this.diGraph = diGraph;
        connected = new boolean[diGraph.V()];

        dfs(s);
    }

    private void dfs(Vertex v) {
        connected[v.getV()] = true;
        counter++;

        diGraph.getAdj(diGraph.getVertices().get(v.getV()))
                .forEach(w -> {
                    if (!connected[w.getV()]) {
                        dfs(w);
                    }
                });
    }
}
