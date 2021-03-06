package graphs;

import graphs.algos.toposort.SCC;
import graphs.algos.toposort.TopoSort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class TopoSortTest {
    private DiGraph diGraph;

    @Before
    public void createGraph() {
        diGraph = new DiGraph(createVertices());
        diGraph.setEdges(createEdges());
    }

    @Test
    public void topoSortTest() {
        TopoSort topoSort = new TopoSort(diGraph);

        System.out.println("The list of CCKs:");
        topoSort.getCycles()
                .forEach(System.out::print);

        Assert.assertEquals(3, topoSort.getCycles().size());
    }

    @Test
    public void sccMappingTest() {
        SCC scc = new SCC(diGraph);

        System.out.println("Number of CCKs: " + scc.getCounterScc());
        Assert.assertEquals(3, scc.getCounterScc());

        diGraph.getVertices()
                .forEach(vertex -> {
                    System.out.println(vertex.getLabel() + ": "
                            + scc.getSccId()[vertex.getV()]);
                });
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
        edges.add(new Edge(diGraph.getVertexByLabel("j"),
                diGraph.getVertexByLabel("i")));
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
