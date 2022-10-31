package ru.netology.javacore;

import java.io.IOException;

public class Main {
    public static final int MAX_TASKS_AMOUNT = 7;

    public static void main(String[] args) throws IOException {
        Todos todos = new Todos(MAX_TASKS_AMOUNT);
        TodoProcessor processor = new TodoProcessor(todos);
        TodoServer server = new TodoServer(8989, processor);
        server.start();
    }
}
