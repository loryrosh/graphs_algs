package graphs.algos.dfs;

import graphs.DiGraph;
import graphs.Vertex;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class DfsWithPathTo {
    private DiGraph diGraph;

    private Vertex initialVertex;
    private boolean[] connected;
    private int[] edgeTo;

    public DfsWithPathTo(DiGraph diGraph, Vertex s) {
        initialVertex = s;
        this.diGraph = diGraph;
        connected = new boolean[diGraph.V()];
        edgeTo = new int[diGraph.V()];
        Arrays.fill(edgeTo, -1);

        dfs(s);
    }

    public Integer[] pathTo(Vertex v) {
        List<Integer> path = new ArrayList<>();

        int idx = v.getV();
        path.add(idx);
        while (edgeTo[idx] >= 0) {
            path.add(edgeTo[idx]);
            idx = edgeTo[idx];
        }

        System.out.println("Path to " + v.getV() + " from " + initialVertex.getV() + ":");
        for (int i = path.size() - 2; i >= 0; i--) {
            System.out.println(path.get(i));
        }
        System.out.println();
        return path.toArray(new Integer[path.size()]);
    }

    private void dfs(Vertex v) {
        connected[v.getV()] = true;

        diGraph.getAdj(diGraph.getVertices().get(v.getV()))
                .forEach(w -> {
                    if (!connected[w.getV()]) {
                        edgeTo[w.getV()] = v.getV();
                        dfs(w);
                    }
                });
    }
}
