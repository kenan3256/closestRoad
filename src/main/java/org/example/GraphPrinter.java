package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class GraphPrinter {
    public static void printPath(List<Node> nodes) {
        printPath(nodes, null);
    }

    public static void printPath(List<Node> nodes, Integer end) {

        if (end != null) {
            Node node = nodes.get(end);
            printPath(node);
        } else {
            nodes.forEach(node -> {
                printPath(node);
            });
        }
    }

    private static void printPath(Node node) {
        String path = node.getShortestPath().stream()
                .map(n -> String.valueOf(n.getName()))
                .collect(Collectors.joining("->"));
        path += "->" + node.getName();
        int distance = node.getDistance();
        System.out.println("%s : dəyəri = %s : %s".formatted(node.getName(), distance, path.isEmpty() ? "" : "-> " + path));
    }
}
