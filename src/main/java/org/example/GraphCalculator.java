package org.example;
import java.util.*;
import java.util.stream.Stream;

public class GraphCalculator {
    public static void calculateShortestPaths(Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes().entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }
    private static void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode){
        Integer newDistance= sourceNode.getDistance()+edgeWeight;
        if (newDistance<adjacentNode.getDistance()){
            adjacentNode.setDistance(newDistance);
            List<Node> list = new ArrayList<>();
            for (Node node : Arrays.asList(sourceNode)) {
                list.add(node);
            }
            adjacentNode.setShortestPath(
                    Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList()
            );
        }
    }
}
