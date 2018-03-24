package graphs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Edge {
    private Vertex head;
    private Vertex tail;

    public Edge(Vertex head, Vertex tail) {
        this.head = head;
        this.tail = tail;
    }
}
