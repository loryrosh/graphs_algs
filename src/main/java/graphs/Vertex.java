package graphs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vertex {
    private int v;

    public Vertex(int v) {
        this.v = v;
    }
}
