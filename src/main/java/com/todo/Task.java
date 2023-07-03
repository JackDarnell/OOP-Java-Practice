package com.todo;

public class Task {

    enum Priority {
        LOW,
        MEDUM,
        HIGH
    }

    private String name;
    private String description;
    private Priority priority;

}
