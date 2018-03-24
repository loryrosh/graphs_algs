package graphs;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DiGraph {

    private List<Vertex> vertices;
    private List<Edge> edges;

    public DiGraph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public int V() {
        return vertices.size();
    }

    public int E() {
        return edges.size();
    }

    public List<Vertex> getAdj(Vertex v) {
        Vertex vertex = vertices.get(vertices.indexOf(v));
        return edges.stream()
                .filter(edge -> edge.getHead().equals(vertex))
                .map(Edge::getTail)
                .collect(Collectors.toList());
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

    private boolean edgeExists(Vertex head, Vertex tail) {
        return edges.stream()
                .anyMatch(edge -> edge.getHead().equals(head) && edge.getTail().equals(tail));
    }

    private List<Edge> getEdgesByVertex(Vertex v) {
        return edges.stream()
                .filter(edge -> edge.getHead().equals(v) || edge.getTail().equals(v))
                .distinct()
                .collect(Collectors.toList());
    }
}
