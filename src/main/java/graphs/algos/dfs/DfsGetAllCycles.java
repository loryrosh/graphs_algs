package graphs.algos.dfs;

import graphs.DiGraph;
import graphs.Vertex;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter
public class DfsGetAllCycles {
    private DiGraph diGraph;
    private List<List<Integer>> cycles = new ArrayList<>();

    private boolean[] passed;
    private boolean[] onStack;
    private int[] edgeTo;

    boolean isCycle = false;

    public DfsGetAllCycles(DiGraph diGraph) {
        this.diGraph = diGraph;

        passed = new boolean[diGraph.V()];
        onStack = new boolean[diGraph.V()];
        edgeTo = new int[diGraph.V()];
        Arrays.fill(edgeTo, -1);

        diGraph.getVertices().forEach(v -> {
            if (!passed[v.getV()]) {
                dfs(v);
                isCycle = false;
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
                            saveCycle(v, w);
                        }
                    }
                });
        onStack[v.getV()] = false;
    }

    private void saveCycle(Vertex cycleStart, Vertex cycleEnd) {
        int idx = cycleStart.getV();
        LinkedList<Integer> cycle = new LinkedList<>();
        cycle.add(idx);

        while (edgeTo[idx] >= cycleEnd.getV()) {
            cycle.addFirst(edgeTo[idx]);
            idx = edgeTo[idx];
        }
        cycle.add(idx);
        cycles.add(cycle);
    }
}
