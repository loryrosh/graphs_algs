package graphs.algos.toposort;

import graphs.DiGraph;
import graphs.Vertex;
import lombok.Getter;

import java.util.LinkedList;

@Getter
public class TopoSort {
    private DiGraph diGraph;

    private boolean[] passed;
    private LinkedList<Vertex> sorted;

    public TopoSort(DiGraph diGraph) {
        this.diGraph = diGraph;
        passed = new boolean[diGraph.V()];
        sorted = new LinkedList<>();

        diGraph.getVertices().forEach(vertex -> {
            if (!passed[vertex.getV()]) {
                dfs(vertex);
            }
        });
    }

    private void dfs(Vertex v) {
        passed[v.getV()] = true;

        diGraph.getAdj(diGraph.getVertices().get(v.getV()))
                .forEach(w -> {
                    if (!passed[w.getV()]) {
                        dfs(w);
                    }
                });
        sorted.addFirst(v);
    }
}
