package edu.vinuni.pathfinder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Graph implements IGraph {
    private Map<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addNode(String name) {
        nodes.putIfAbsent(name, new Node(name));
    }

    public void addEdge(String from, String to, String vehicleType, int weight) {
        addNode(from);
        addNode(to);

        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        Edge fromEdge = fromNode.getEdge(toNode);
        if (fromEdge == null) {
            fromEdge = new Edge(toNode, vehicleType, weight);
            fromNode.addEdge(fromEdge);
        } else {
            fromEdge.addVehicleWeight(vehicleType, weight);
        }

        Edge toEdge = toNode.getEdge(fromNode);
        if (toEdge == null) {
            toEdge = new Edge(fromNode, vehicleType, weight);
            toNode.addEdge(toEdge);
        } else {
            toEdge.addVehicleWeight(vehicleType, weight);
        }
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public Collection<Node> getNodes() {
        return nodes.values();
    }
}
