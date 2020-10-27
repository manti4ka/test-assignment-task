package org.test.assignment.services;

import lombok.Data;

import java.util.List;

@Data
public class JobRequest {
    private List<Task> tasks;
}
