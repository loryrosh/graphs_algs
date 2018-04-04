package graphs.algos.toposort;

import graphs.DiGraph;
import graphs.Vertex;
import lombok.Getter;

@Getter
public class SCC {
    private DiGraph diGraph;

    private boolean[] passed;
    private int[] sccId;
    private int counterScc = 0;

    public SCC(DiGraph diGraph) {
        this.diGraph = diGraph;

        passed = new boolean[diGraph.V()];
        sccId = new int[diGraph.V()];

        diGraph.reverse();
        TopoSort ts = new TopoSort(diGraph);
        diGraph.reverse();

        while (!ts.getSorted().isEmpty()) {
            Vertex k = ts.getSorted().poll();
            if (k!= null && !passed[k.getV()]) {
                dfs(k);
                ++counterScc;
            }
        }
    }

    private void dfs(Vertex v) {
        passed[v.getV()] = true;
        sccId[v.getV()] = counterScc;

        for (Vertex w : diGraph.getAdj(v)) {
            if (!passed[w.getV()]) {
                dfs(w);
            }
        }
    }
}
