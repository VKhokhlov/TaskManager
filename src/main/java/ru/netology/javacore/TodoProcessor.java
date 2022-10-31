package ru.netology.javacore;

import com.google.gson.Gson;

public class TodoProcessor {
    private final Todos todos;
    private final Gson gson = new Gson();

    public TodoProcessor(Todos todos) {
        this.todos = todos;
    }

    public String process(String requestText) {
        TodoRequest todoRequest = gson.fromJson(requestText, TodoRequest.class);

        switch (todoRequest.type) {
            case ADD: {
                todos.addTask(todoRequest.task);
                break;
            }
            case REMOVE: {
                todos.removeTask(todoRequest.task);
                break;
            }
        }

        return todos.getAllTasks();
    }

}
