package edu.vinuni.pathfinder;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

class Dijkstra implements IShortestPathAlgorithm {
    public Map<Node, Integer> findShortestPath(IGraph graph, Node source, String vehicleType) {
        Map<Node, Integer> distances = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            int currentDistance = distances.get(current);

            for (Edge edge : current.getEdges()) {
                if (edge.hasVehicleWeight(vehicleType)) {
                    int weight = edge.getWeight(vehicleType);
                    if (weight != Integer.MAX_VALUE) {
                        Node target = edge.getTargetNode();
                        int newDist = currentDistance + weight;
                        if (newDist < distances.get(target)) {
                            distances.put(target, newDist);
                            priorityQueue.add(target);
                        }
                    }
                }
            }
        }

        distances.entrySet().removeIf(entry -> entry.getValue() == Integer.MAX_VALUE);

        return distances;
    }
}
