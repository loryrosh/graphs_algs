package graphs;

import graphs.algos.dfs.DfsGetAllCycles;
import graphs.algos.dfs.DfsHasCycles;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GiGraphCyclesTest {

    private DiGraph diGraph;

    @Before
    public void createGraph() {
        List<Vertex> vertices = createVertices();
        diGraph = new DiGraph(vertices, createEdges(vertices));
    }

    @Test
    public void testHasCycles() {
        DfsHasCycles dfsHasCycles = new DfsHasCycles(diGraph, diGraph.getVertices().get(0));
        Assert.assertTrue(dfsHasCycles.isCycle());
    }

    @Test
    public void testGetAllCycles() {
        DfsGetAllCycles dfsGetAllCycles = new DfsGetAllCycles(diGraph);
        Assert.assertEquals(2, dfsGetAllCycles.getCycles().size());

        System.out.println(dfsGetAllCycles.getCycles());

        // bug when cycles have a common vertex
        // TODO
        Optional<Edge> edge4_5_toRemove = diGraph.getEdges().stream()
                .filter(edge -> edge.getHead().equals(diGraph.getVertices().get(4)))
                .findFirst();

        edge4_5_toRemove.ifPresent(edge -> {
            diGraph.delEdge(edge);
            diGraph.addEdge(diGraph.getVertices().get(4), diGraph.getVertices().get(6));
        });

        dfsGetAllCycles = new DfsGetAllCycles(diGraph);
        Assert.assertEquals(1, dfsGetAllCycles.getCycles().size());

        System.out.println(dfsGetAllCycles.getCycles());
    }

    private List<Vertex> createVertices() {
        List<Vertex> vertices = new LinkedList<>();

        Stream.iterate(0, n -> n + 1)
                .limit(9)
                .forEach(i -> vertices.add(new Vertex(i)));

        return vertices;
    }

    private List<Edge> createEdges(List<Vertex> vertices) {
        LinkedList<Edge> edges = new LinkedList<>();
        edges.add(new Edge(vertices.get(0), vertices.get(4)));
        edges.add(new Edge(vertices.get(5), vertices.get(0)));
        edges.add(new Edge(vertices.get(6), vertices.get(0)));
        edges.add(new Edge(vertices.get(4), vertices.get(5)));
        edges.add(new Edge(vertices.get(7), vertices.get(6)));
        edges.add(new Edge(vertices.get(6), vertices.get(8)));
        edges.add(new Edge(vertices.get(8), vertices.get(7)));
        return edges;
    }
}
