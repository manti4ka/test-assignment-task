package org.test.assignment.services;

import lombok.Data;

@Data
public class TaskResponse {

    public TaskResponse(String name, String command) {
        this.name = name;
        this.command = command;
    }

    private String name;
    private String command;
}
