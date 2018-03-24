package graphs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class DiGraphTest {

    private DiGraph diGraph;

    @Before
    public void createGraph() {
        List<Vertex> vertices = createVertices();
        diGraph = new DiGraph(vertices, createEdges(vertices));
    }

    @Test
    public void testGraphCreation() {
        Assert.assertEquals(9, diGraph.V());
        Assert.assertEquals(12, diGraph.E());
    }

    @Test
    public void testGetAdjList() {
        Assert.assertEquals(4, diGraph.getAdj(diGraph.getVertices().get(0)).size());
        Assert.assertEquals(2, diGraph.getAdj(diGraph.getVertices().get(6)).size());
        Assert.assertEquals(1, diGraph.getAdj(diGraph.getVertices().get(5)).size());
        Assert.assertEquals(2, diGraph.getAdj(diGraph.getVertices().get(2)).size());
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
        edges.add(new Edge(vertices.get(0), vertices.get(1)));
        edges.add(new Edge(vertices.get(0), vertices.get(2)));
        edges.add(new Edge(vertices.get(0), vertices.get(3)));
        edges.add(new Edge(vertices.get(0), vertices.get(4)));
        edges.add(new Edge(vertices.get(5), vertices.get(0)));
        edges.add(new Edge(vertices.get(6), vertices.get(0)));
        edges.add(new Edge(vertices.get(2), vertices.get(1)));
        edges.add(new Edge(vertices.get(2), vertices.get(3)));
        edges.add(new Edge(vertices.get(4), vertices.get(5)));
        edges.add(new Edge(vertices.get(7), vertices.get(6)));
        edges.add(new Edge(vertices.get(6), vertices.get(8)));
        edges.add(new Edge(vertices.get(8), vertices.get(7)));
        return edges;
    }
}
