package edu.vinuni.pathfinder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Edge {
    private Node targetNode;
    private Map<String, Integer> vehicleWeights;

    public Edge(Node targetNode, String vehicleType, int weight) {
        this.targetNode = targetNode;
        this.vehicleWeights = new HashMap<>();
        this.vehicleWeights.put(vehicleType, weight);
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public int getWeight(String vehicleType) {
        return vehicleWeights.getOrDefault(vehicleType, Integer.MAX_VALUE);
    }

    public boolean hasVehicleWeight(String vehicleType) {
        return vehicleWeights.containsKey(vehicleType);
    }

    public void addVehicleWeight(String vehicleType, int weight) {
        this.vehicleWeights.put(vehicleType, weight);
    }

    public Set<String> getVehicleTypes() {
        return vehicleWeights.keySet();
    }
}
