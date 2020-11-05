package org.test.assignment.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.assignment.TestData;
import org.test.assignment.custom.ValidationException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Test
    public void getTopologicalSortTest() throws ValidationException {

        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1"));
        List<Task> res = taskService.getTopologicalSort(tasks);

        assertEquals(res.size(), 1);
    }

    @Test
    public void getBashWithTopologicalSortTest() throws ValidationException {

        List<Task> tasks = new ArrayList<>();
        tasks.add(TestData.generateTask("task-1"));
        tasks.add(TestData.generateTask("task-2"));
        byte[] res = taskService.getBashWithTopologicalSort(tasks);
        String fileAsString = new String(res, StandardCharsets.UTF_8);

        assertTrue(fileAsString.contains("#!/usr/bin/env bash"));
        assertTrue(fileAsString.contains("randomtask-1"));
        assertTrue(fileAsString.contains("randomtask-2"));

    }

    @Test
    public void getBashWithTopologicalSortTest_noTasks() throws ValidationException {

        List<Task> tasks = new ArrayList<>();
        byte[] res = taskService.getBashWithTopologicalSort(tasks);
        String fileAsString = new String(res, StandardCharsets.UTF_8);

        assertTrue(fileAsString.contains("#!/usr/bin/env bash"));

    }
}
