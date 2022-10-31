package ru.netology.javacore;

public class TodoRequest {
    protected TodoRequestType type;
    protected String task;

    public TodoRequest(TodoRequestType type, String task) {
        this.type = type;
        this.task = task;
    }
}
