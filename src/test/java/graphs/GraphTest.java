package graphs;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class GraphTest {

    @Test
    public void testGraphCreation() {
        List<Vertex> vertices = createVertices();
        Graph graph = new Graph(vertices, createEdges(vertices));
        Assert.assertEquals(7, graph.V());
        Assert.assertEquals(8, graph.E());
    }

    private List<Vertex> createVertices() {
        List<Vertex> vertices = new LinkedList<>();

        Stream.iterate(0, n -> n + 1)
                .limit(7)
                .forEach(i -> vertices.add(new Vertex(i)));

        return vertices;
    }

    private List<Edge> createEdges(List<Vertex> vertices) {
        LinkedList<Edge> edges = new LinkedList<>();
        edges.add(new Edge(vertices.get(0), vertices.get(1)));
        edges.add(new Edge(vertices.get(0), vertices.get(2)));
        edges.add(new Edge(vertices.get(0), vertices.get(3)));
        edges.add(new Edge(vertices.get(0), vertices.get(4)));
        edges.add(new Edge(vertices.get(0), vertices.get(5)));
        edges.add(new Edge(vertices.get(1), vertices.get(2)));
        edges.add(new Edge(vertices.get(2), vertices.get(3)));
        edges.add(new Edge(vertices.get(4), vertices.get(5)));
        return edges;
    }
}
