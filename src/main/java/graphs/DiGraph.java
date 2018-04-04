package graphs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DiGraph {

    private List<Vertex> vertices;
    private List<Edge> edges;

    public DiGraph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public DiGraph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public int V() {
        return vertices.size();
    }

    public int E() {
        return edges.size();
    }

    public List<Vertex> getAdj(Vertex v) {
        return edges.stream()
                .filter(edge -> edge.getHead().equals(v))
                .map(Edge::getTail)
                .collect(Collectors.toList());
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public Vertex getVertexByLabel(String labelToSearch) {
        return vertices.stream()
                .filter(vertex -> labelToSearch.equals(vertex.getLabel()))
                .findAny().get();
    }

    public void delVertex(Vertex v) {
        Vertex vertexToRemove = vertices.get(v.getV());
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

    public void reverse() {
        edges.forEach(edge -> {
            Vertex tmp = edge.getHead();
            edge.setHead(edge.getTail());
            edge.setTail(tmp);
        });
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
