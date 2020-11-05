package org.test.assignment.services;

import org.springframework.stereotype.Component;
import org.test.assignment.custom.ValidationException;
import org.test.assignment.func.Graph;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskService {

    public final static String START_LINE = "#!/usr/bin/env bash";
    public final static String NEW_LINE = "\n";

    public List<TaskResponse> getTopologicalSort(List<Task> tasks) throws ValidationException {
        final Graph graph = new Graph(tasks);
        return graph.topologicalSort().stream().map( x -> new TaskResponse(x.getName(), x.getCommand())).collect(Collectors.toList());
    }

    public byte[] getBashWithTopologicalSort(List<Task> tasks) throws ValidationException {

        final List<TaskResponse> res = getTopologicalSort(tasks);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String content =  res.stream().map(x -> x.getCommand())
                .reduce(START_LINE + NEW_LINE + NEW_LINE,
                        (acc, element) ->  acc + element + NEW_LINE);
        try (PrintStream out = new PrintStream(baos)) {
            out.print(content);
        }
        return baos.toByteArray();
    }
}
