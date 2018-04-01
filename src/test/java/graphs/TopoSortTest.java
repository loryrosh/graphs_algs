package graphs;

import graphs.algos.toposort.TopoSort;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class TopoSortTest {
    private DiGraph diGraph;

    @Before
    public void createGraph() {
        List<Vertex> vertices = createVertices();
        diGraph = new DiGraph(vertices, createEdges());
    }

    @Test
    public void topoSortTest() {
        TopoSort topoSort = new TopoSort(diGraph);
        System.out.println(topoSort.getSorted());
    }

    private List<Vertex> createVertices() {
        List<Vertex> vertices = new LinkedList<>();

        Stream.iterate(0, n -> n + 1)
                .limit(9)
                .forEach(i -> vertices.add(new Vertex(i)));

        vertices.get(0).setLabel("i");
        vertices.get(1).setLabel("j");
        vertices.get(2).setLabel("k");
        vertices.get(3).setLabel("l");
        vertices.get(4).setLabel("m");
        vertices.get(5).setLabel("n");
        vertices.get(6).setLabel("o");
        vertices.get(7).setLabel("p");
        vertices.get(8).setLabel("q");
        return vertices;
    }

    private List<Edge> createEdges() {
        LinkedList<Edge> edges = new LinkedList<>();
        edges.add(new Edge(diGraph.getVertexByLabel("i"),
                diGraph.getVertexByLabel("j")));
        edges.add(new Edge(diGraph.getVertexByLabel("j"),
                diGraph.getVertexByLabel("k")));
        edges.add(new Edge(diGraph.getVertexByLabel("k"),
                diGraph.getVertexByLabel("l")));
        edges.add(new Edge(diGraph.getVertexByLabel("l"),
                diGraph.getVertexByLabel("m")));
        edges.add(new Edge(diGraph.getVertexByLabel("m"),
                diGraph.getVertexByLabel("j")));
        edges.add(new Edge(diGraph.getVertexByLabel("n"),
                diGraph.getVertexByLabel("k")));
        edges.add(new Edge(diGraph.getVertexByLabel("n"),
                diGraph.getVertexByLabel("q")));
        edges.add(new Edge(diGraph.getVertexByLabel("q"),
                diGraph.getVertexByLabel("p")));
        edges.add(new Edge(diGraph.getVertexByLabel("p"),
                diGraph.getVertexByLabel("o")));
        edges.add(new Edge(diGraph.getVertexByLabel("o"),
                diGraph.getVertexByLabel("n")));
        return edges;
    }

}
