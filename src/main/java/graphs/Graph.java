package graphs;

import java.util.List;
import java.util.stream.Collectors;

public class Graph {

    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public int V() {
        return vertices.size();
    }

    public int E() {
        return edges.size();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void delVertex(Vertex v) {
        Vertex vertexToRemove = vertices.get(vertices.indexOf(v));
        List<Edge> adjEdges = getEdgesByVertex(vertexToRemove);
        edges.removeAll(adjEdges);
        vertices.remove(vertexToRemove);
    }

    public void addEdge(Vertex v, Vertex w) {
        if (!vertices.contains(v) && !vertices.contains(w)) {
            return;
        }

        if (!edgeExists(v, w)) {
            edges.add(new Edge(v, w));
        }
    }

    public void delEdge(Edge e) {
        edges.remove(e);
    }

    private boolean edgeExists(Vertex v, Vertex w) {
        long numFoundEdges = edges.stream()
                .filter(edge ->
                        (edge.getHead().equals(v) && edge.getTail().equals(w)) ||
                                (edge.getHead().equals(w) && edge.getTail().equals(v))
                )
                .count();
        return numFoundEdges > 0;
    }

    private List<Edge> getEdgesByVertex(Vertex v) {
        return edges.stream()
                .filter(edge -> edge.getHead().equals(v) || edge.getTail().equals(v))
                .distinct()
                .collect(Collectors.toList());
    }
}
