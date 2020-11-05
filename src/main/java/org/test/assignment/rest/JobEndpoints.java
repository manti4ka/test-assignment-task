package org.test.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.test.assignment.custom.ValidationException;
import org.test.assignment.services.JobRequest;
import org.test.assignment.services.Task;
import org.test.assignment.services.TaskResponse;
import org.test.assignment.services.TaskService;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

@RestController
public class JobEndpoints {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/task", headers = "content-type=application/json")
    public List<TaskResponse> processTasks(@RequestBody JobRequest jobRequest) throws ValidationException {
        return taskService.getTopologicalSort(jobRequest.getTasks());
    }

    @PostMapping(value = "/task/bash", headers = "content-type=application/json")
    @ResponseBody
    public byte[] processTasksAsBash(@RequestBody JobRequest jobRequest) throws ValidationException {
        return taskService.getBashWithTopologicalSort(jobRequest.getTasks());
    }


}
