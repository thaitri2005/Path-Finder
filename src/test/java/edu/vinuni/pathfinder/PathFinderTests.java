package edu.vinuni.pathfinder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

class PathFinderTests {

    private Node nodeA;
    private Node nodeB;
    private Edge edge;
    private Graph graph;
    private Dijkstra dijkstra;

    @BeforeEach
    void setUp() {
        nodeA = new Node("A");
        nodeB = new Node("B");
        edge = new Edge(nodeB, "car", 10);
        graph = new Graph();
        dijkstra = new Dijkstra();
    }

    @Test
    void testNode() {
        assertEquals("A", nodeA.getName());
        assertTrue(nodeA.getEdges().isEmpty());

        nodeA.addEdge(edge);
        assertEquals(1, nodeA.getEdges().size());
        assertEquals(nodeB, nodeA.getEdge(nodeB).getTargetNode());
    }

    @Test
    void testEdge() {
        assertEquals(nodeB, edge.getTargetNode());
        assertEquals(10, edge.getWeight("car"));
        assertTrue(edge.hasVehicleWeight("car"));

        edge.addVehicleWeight("truck", 15);
        assertEquals(15, edge.getWeight("truck"));
    }

    @Test
    void testGraph() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("A", "B", "car", 10);

        assertEquals(2, graph.getNodes().size());
        assertEquals("A", graph.getNode("A").getName());
        assertEquals("B", graph.getNode("B").getName());

        Node retrievedNode = graph.getNode("A");
        Edge retrievedEdge = retrievedNode.getEdge(graph.getNode("B"));
        assertEquals(10, retrievedEdge.getWeight("car"));
    }

    @Test
    void testDijkstra() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("A", "B", "car", 10);

        Node source = graph.getNode("A");
        Map<Node, Integer> shortestPaths = dijkstra.findShortestPath(graph, source, "car");

        assertEquals(0, shortestPaths.get(source).intValue());
        assertEquals(10, shortestPaths.get(graph.getNode("B")).intValue());
    }

    @Test
    void testDijkstraNoPath() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("A", "C", "car", 5);

        Node source = graph.getNode("A");
        Map<Node, Integer> shortestPaths = dijkstra.findShortestPath(graph, source, "car");

        shortestPaths.forEach((node, distance) -> System.out.println(node.getName() + " : " + distance));

        assertFalse(shortestPaths.containsKey(graph.getNode("B")));
    }
}
