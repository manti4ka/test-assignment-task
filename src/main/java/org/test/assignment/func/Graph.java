package org.test.assignment.func;

import org.test.assignment.custom.ValidationException;
import org.test.assignment.services.Task;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private Map<String, Task> tasksMap;
    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph(List<Task> tasks) {

        adjVertices = new HashMap<>();
        tasksMap = tasks.stream()
                .collect(Collectors.toMap(y -> y.getName(), y -> y));

        for(var task : tasks) {
            addVertex(task.getName());
            if(Objects.nonNull(task.getRequires())) {
                for (var dep : task.getRequires()) {
                    addEdge(task.getName(), dep);
                }
            }
        }
    }

    public List<Task> topologicalSort() throws ValidationException {

        final Map<String, Integer> inAdjMap = generateAdjDegree();
        final Queue<String> queue = new LinkedList<>();
        int visitedVertices = 0;

        //collect all vertices with in adjacency == 0
        for(var el : tasksMap.keySet()) {
            if (Objects.isNull(inAdjMap.get(el)) || inAdjMap.get(el) == 0) {
                queue.add(el);
            }
        }
        final List<Task> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            res.add(tasksMap.get(current));
            final List<Vertex> adj = getAdjVertices(current);

            //decrease with 1 the degree of all the adjacent of the current vertex
            if(Objects.nonNull(adj)) {
                for (var vertex : adj) {
                    Integer value = inAdjMap.get(vertex.getName()) - 1;
                    inAdjMap.put(vertex.getName(), value);
                    if (value == 0) {
                        queue.add(vertex.getName());
                    }
                }
            }
            visitedVertices++;
        }
        if(tasksMap.size() != visitedVertices) {
            throw new ValidationException("Invalid input.");
        }
        return res;
    }

    private Map<String, Integer> generateAdjDegree() {
        final Map<String, Integer> inAdj = new HashMap<>();
        for (var key : adjVertices.keySet()) {
            for(var adjacent : adjVertices.get(key)) {
                Integer count = inAdj.get(adjacent);
                if(Objects.isNull(count)) {
                    inAdj.put(adjacent.getName(), 1);
                } else {
                    inAdj.put(adjacent.getName(), count + 1);
                }
            }
        }
        return inAdj;
    }

    private void addVertex(String name) {
        adjVertices.putIfAbsent(new Vertex(name), new ArrayList<>());
    }

    private void addEdge(String name1, String name2) {
        final Vertex v1 = new Vertex(name1);
        final Vertex v2 = new Vertex(name2);
        if(Objects.isNull(adjVertices.get(v2))) {
            addVertex(name2);
        }
        adjVertices.get(v2).add(v1);

    }

    private List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

}
