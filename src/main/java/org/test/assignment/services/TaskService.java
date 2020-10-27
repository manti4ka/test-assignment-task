package org.test.assignment.services;

import org.springframework.stereotype.Component;
import org.test.assignment.custom.ValidationException;
import org.test.assignment.func.Graph;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

@Component
public class TaskService {

    public final static String START_LINE = "#!/usr/bin/env bash";
    public final static String NEW_LINE = "\n";

    public List<Task> getTopologicalSort(List<Task> tasks) throws ValidationException {
        Graph graph = new Graph(tasks);
        return graph.topologicalSort();
    }

    public byte[] getBashWithTopologicalSort(List<Task> tasks) throws ValidationException {

        List<Task> res = getTopologicalSort(tasks);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String content =  res.stream().map(x -> x.getCommand())
                .reduce(START_LINE + NEW_LINE + NEW_LINE,
                        (acc, element) ->  acc + element + NEW_LINE);
        try (PrintStream out = new PrintStream(baos)) {
            out.print(content);
        }
        return baos.toByteArray();
    }
}
