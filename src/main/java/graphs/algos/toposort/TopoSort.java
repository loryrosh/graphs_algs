package graphs.algos.toposort;

import graphs.DiGraph;
import graphs.Vertex;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
public class TopoSort {
    private DiGraph diGraph;

    private boolean[] passed;
    private LinkedList<Vertex> sorted;
    private List<List<String>> cycles = new ArrayList<>();
    private List<String> curCycle;

    public TopoSort(DiGraph diGraph) {
        this.diGraph = diGraph;
        passed = new boolean[diGraph.V()];
        sorted = new LinkedList<>();

        diGraph.getVertices().forEach(vertex -> {
            if (!passed[vertex.getV()]) {
                curCycle = new ArrayList<>();
                curCycle.add(vertex.getLabel());

                dfs(vertex);

                cycles.add(curCycle);
            }
        });
    }

    private void dfs(Vertex v) {
        passed[v.getV()] = true;

        diGraph.getAdj(diGraph.getVertices().get(v.getV()))
                .forEach(w -> {
                    if (!passed[w.getV()]) {
                        curCycle.add(w.getLabel());
                        dfs(w);
                    }
                });
        sorted.addFirst(v);
    }
}
