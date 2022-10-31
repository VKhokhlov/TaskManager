package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final Set<String> tasks;
    private final int maxTasksAmount;

    public Todos(int maxTasksAmount) {
        this.maxTasksAmount = maxTasksAmount;
        this.tasks = new TreeSet<String>();
    }

    public void addTask(String task) {
        if (tasks.size() < maxTasksAmount) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        return String.join(" ", tasks);
    }

    public Set<String> getTasks() {
        return tasks;
    }
}
