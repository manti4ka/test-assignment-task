package org.test.assignment;

import org.test.assignment.services.Task;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static Task generateTask(String name, String ... requires) {
        final Task task = new Task();
        task.setName(name);
        task.setCommand("random" + name);
        final List<String> r = new ArrayList<>();
        for(var el : requires) {
            r.add(el);
        }
        task.setRequires(r);
        return task;
    }
}
