package org.test.assignment.func;

import lombok.Data;

@Data
public class Vertex {

    private String name;

    public Vertex(String name) {
        this.name = name;
    }
}
