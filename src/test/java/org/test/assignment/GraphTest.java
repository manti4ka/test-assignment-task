package org.test.assignment;

import org.junit.Test;
import org.test.assignment.custom.ValidationException;
import org.test.assignment.func.Graph;
import org.test.assignment.services.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphTest {


    @Test
    public void testNoTask() throws ValidationException {
        List<Task> tasks = new ArrayList<>();

        Graph testGraph  = new Graph(tasks);
        List<Task> res = testGraph.topologicalSort();
        assertEquals(res.size(), 0);

    }

    @Test
    public void testOneTask() throws ValidationException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1"));

        Graph testGraph  = new Graph(tasks);
        List<Task> res = testGraph.topologicalSort();

        assertEquals(res.size(), 1);
        assertEquals(res.get(0).getName(), "task-1");
    }


    @Test
    public void testThreeTasks() throws ValidationException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1"));
        tasks.add(TestData.generateTask("task-2", "task-3"));
        tasks.add(TestData.generateTask("task-3", "task-1"));
        Graph testGraph  = new Graph(tasks);
        List<Task> res = testGraph.topologicalSort();

        assertEquals(res.size(), 3);
        assertEquals(res.get(0).getName(), "task-1");
        assertEquals(res.get(1).getName(), "task-3");
        assertEquals(res.get(2).getName(), "task-2");

    }

    @Test
    public void testThreeTasksInOrder() throws ValidationException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1"));
        tasks.add(TestData.generateTask("task-3", "task-1"));
        tasks.add(TestData.generateTask("task-2", "task-3"));
        Graph testGraph  = new Graph(tasks);
        List<Task> res = testGraph.topologicalSort();

        assertEquals(res.size(), 3);
        assertEquals(res.get(0).getName(), "task-1");
        assertEquals(res.get(1).getName(), "task-3");
        assertEquals(res.get(2).getName(), "task-2");
    }

    @Test
    public void testFourTasks() throws ValidationException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1"));
        tasks.add(TestData.generateTask("task-2", "task-3"));
        tasks.add(TestData.generateTask("task-3", "task-1"));
        tasks.add(TestData.generateTask("task-4", "task-2", "task-3"));
        Graph testGraph  = new Graph(tasks);

        List<Task> res = testGraph.topologicalSort();

        assertEquals(res.get(0).getName(), "task-1");
        assertEquals(res.get(1).getName(), "task-3");
        assertEquals(res.get(2).getName(), "task-2");
        assertEquals(res.get(3).getName(), "task-4");
    }

    @Test
    public void testFourTasksSwapped() throws ValidationException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1"));
        tasks.add(TestData.generateTask("task-2", "task-3"));
        tasks.add(TestData.generateTask("task-3", "task-1"));
        tasks.add(TestData.generateTask("task-4",  "task-3", "task-2"));
        Graph testGraph  = new Graph(tasks);

        List<Task> res = testGraph.topologicalSort();

        assertEquals(res.get(0).getName(), "task-1");
        assertEquals(res.get(1).getName(), "task-3");
        assertEquals(res.get(2).getName(), "task-2");
        assertEquals(res.get(3).getName(), "task-4");
    }

    @Test(expected= ValidationException.class)
    public void testInvalidInput() throws ValidationException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1", "task-2"));
        tasks.add(TestData.generateTask("task-2", "task-3"));
        tasks.add(TestData.generateTask("task-3", "task-1"));

        Graph testGraph  = new Graph(tasks);
        testGraph.topologicalSort();

    }
}
