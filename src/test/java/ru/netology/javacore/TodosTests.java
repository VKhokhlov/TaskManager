package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

public class TodosTests {

    @ParameterizedTest
    @DisplayName("addTask")
    @MethodSource("addTaskParams")
    public void addTask(Map<TodoRequest, Set<String>> params) {
        Todos todos = new Todos(Main.MAX_TASKS_AMOUNT);

        params.forEach((todoRequest, targetSet) -> {
            todos.addTask(todoRequest.task);
            Assertions.assertEquals(targetSet, todos.getTasks());
        });
    }

    @ParameterizedTest
    @DisplayName("removeTask")
    @MethodSource("removeTaskParams")
    public void removeTask(Map<TodoRequest, Set<String>> params) {
        Todos todos = new Todos(Main.MAX_TASKS_AMOUNT);

        params.forEach((todoRequest, targetSet) -> {
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
            Assertions.assertEquals(targetSet.toString(), todos.getTasks().toString());
        });
    }

    @ParameterizedTest
    @DisplayName("getAllTasks")
    @MethodSource("removeTaskParams")
    public void getAllTasks(Map<TodoRequest, Set<String>> params) {
        Todos todos = new Todos(Main.MAX_TASKS_AMOUNT);

        params.forEach((todoRequest, targetSet) -> {
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
            Assertions.assertEquals(String.join(" ", targetSet), todos.getAllTasks());
        });
    }

    private static Stream<Arguments> addTaskParams() {
        String testCases1[] = {
                "ADD, Первая, Первая",
                "ADD, Вторая, Вторая Первая",
                "ADD, Третья, Вторая Первая Третья",
                "ADD, Четвертая, Вторая Первая Третья Четвертая",
                "ADD, Пятая, Вторая Первая Пятая Третья Четвертая",
                "ADD, Шестая, Вторая Первая Пятая Третья Четвертая Шестая",
                "ADD, Седьмая, Вторая Первая Пятая Седьмая Третья Четвертая Шестая",
                "ADD, Восьмая, Вторая Первая Пятая Седьмая Третья Четвертая Шестая"
        };

        String testCases2[] = {
                "ADD, 1, 1",
                "ADD, 2, 1 2",
                "ADD, 3, 1 2 3",
                "ADD, 4, 1 2 3 4",
                "ADD, 5, 1 2 3 4 5",
                "ADD, 6, 1 2 3 4 5 6",
                "ADD, 7, 1 2 3 4 5 6 7",
                "ADD, 8, 1 2 3 4 5 6 7"
        };

        return Stream.of(
                Arguments.of(packParams(testCases1)),
                Arguments.of(packParams(testCases2))
        );
    }

    private static Stream<Arguments> removeTaskParams() {
        String testCases1[] = {
                "ADD, Первая, Первая",
                "ADD, Вторая, Вторая Первая",
                "ADD, Третья, Вторая Первая Третья",
                "REMOVE, Первая, Вторая Третья",
                "ADD, Четвертая, Вторая Третья Четвертая",
                "ADD, Пятая, Вторая Пятая Третья Четвертая",
                "REMOVE, Вторая, Пятая Третья Четвертая",
                "REMOVE, Третья, Пятая Четвертая",
                "ADD, Шестая, Пятая Четвертая Шестая",
                "REMOVE, Пятая, Четвертая Шестая",
                "ADD, Седьмая, Седьмая Четвертая Шестая",
                "ADD, Восьмая, Восьмая Седьмая Четвертая Шестая",
                "REMOVE, Седьмая, Восьмая Четвертая Шестая",
                "REMOVE, Восьмая, Четвертая Шестая",
                "REMOVE, Четвертая, Шестая",
                "REMOVE, Шестая, "
        };

        String testCases2[] = {
                "ADD, 1, 1",
                "ADD, 2, 1 2",
                "ADD, 3, 1 2 3",
                "REMOVE, 3, 1 2",
                "REMOVE, 2, 1",
                "REMOVE, 1, "
        };

        return Stream.of(
                Arguments.of(packParams(testCases1)),
                Arguments.of(packParams(testCases2))
        );
    }

    private static Map<TodoRequest, Set<String>> packParams(String[] stringParams) {
        Map<TodoRequest, Set<String>> params = new LinkedHashMap<>();

        for (String line:stringParams) {
            String[] values = line.split(",");
            String type = values[0];
            String task = values[1].trim();
            String[] targetTasks = values[2].trim().split(" ");

            TodoRequest todoRequest = new TodoRequest(TodoRequestType.valueOf(type), task);
            Set<String> targetSet = new TreeSet<>(Arrays.asList(targetTasks));

            params.put(todoRequest, targetSet);
        }

        return params;
    }
}
