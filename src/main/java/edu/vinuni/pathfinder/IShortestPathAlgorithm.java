package edu.vinuni.pathfinder;
import java.util.Map;

interface IShortestPathAlgorithm {
    Map<Node, Integer> findShortestPath(IGraph graph, Node source, String vehicleType);
}
