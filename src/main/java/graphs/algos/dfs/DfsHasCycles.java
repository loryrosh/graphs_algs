package graphs.algos.dfs;

import graphs.DiGraph;
import graphs.Vertex;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class DfsHasCycles {

    private DiGraph diGraph;
    private Vertex initialVertex;

    private boolean[] passed;
    private boolean[] onStack;
    private int[] edgeTo;

    boolean isCycle = false;

    public DfsHasCycles(DiGraph diGraph, Vertex s) {
        this.diGraph = diGraph;
        initialVertex = s;

        passed = new boolean[diGraph.V()];
        onStack = new boolean[diGraph.V()];
        edgeTo = new int[diGraph.V()];
        Arrays.fill(edgeTo, -1);

        diGraph.getVertices().forEach(vertex -> {
            if (!passed[vertex.getV()]) {
                dfs(s);
            }
        });
    }

    private void dfs(Vertex v) {
        if (isCycle) {
            return;
        }

        passed[v.getV()] = true;
        onStack[v.getV()] = true;

        diGraph.getAdj(diGraph.getVertices().get(v.getV()))
                .forEach(w -> {
                    if (!passed[w.getV()]) {
                        edgeTo[w.getV()] = v.getV();
                        dfs(w);
                    } else {
                        if (onStack[w.getV()]) {
                            isCycle = true;
                        }
                    }
                });
        onStack[v.getV()] = false;
    }
}
