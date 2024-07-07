package edu.vinuni.pathfinder;
import java.util.ArrayList;
import java.util.List;

class Node {
    private String name;
    private List<Edge> edges;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Edge getEdge(Node targetNode) {
        for (Edge edge : edges) {
            if (edge.getTargetNode().equals(targetNode)) {
                return edge;
            }
        }
        return null;
    }
}
