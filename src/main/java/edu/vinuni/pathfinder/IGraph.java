package edu.vinuni.pathfinder;

import java.util.Collection;

interface IGraph {
    void addNode(String name);
    void addEdge(String from, String to, String vehicleType, int weight);
    Node getNode(String name);
    Collection<Node> getNodes();
}
