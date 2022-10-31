package ru.netology.javacore;

import com.google.gson.Gson;

import java.util.Stack;

public class TodoProcessor {
    private final Todos todos;
    private final Gson gson = new Gson();
    private final Stack<TodoRequest> history = new Stack<>();

    public TodoProcessor(Todos todos) {
        this.todos = todos;
    }

    public String process(String requestText) {
        TodoRequest todoRequest = gson.fromJson(requestText, TodoRequest.class);

        switch (todoRequest.type) {
            case ADD: {
                todos.addTask(todoRequest.task);
                history.push(todoRequest);
                break;
            }
            case REMOVE: {
                todos.removeTask(todoRequest.task);
                history.push(todoRequest);
                break;
            }
            case RESTORE: {
                revert();
                break;
            }
        }
        return todos.getAllTasks();
    }

    private void revert() {
        if (history.empty()) {
            return;
        }

        TodoRequest todoRequest = history.pop();

        switch (todoRequest.type) {
            case ADD: {
                todos.removeTask(todoRequest.task);
                break;
            }
            case REMOVE: {
                todos.addTask(todoRequest.task);
                break;
            }
        }
    }
}
