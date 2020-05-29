package com.chaichai.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public TodoController() {
        todos.add(new Todo(1, "chaichai1"));
        todos.add(new Todo(2, "chaichai2"));
        todos.add(new Todo(3, "chaichai3"));
    }

    @GetMapping("/todo")
    public List<Todo> getTodos() {
        return todos;
    }

    //    GET By ID
    @GetMapping("/todo/{id}")
    public Todo getTodoById(@PathVariable long id) {
//        final long id = 1;
        return todos.stream().filter(result -> result.getId() == id).findFirst().orElseGet(() -> null);
    }

    //  GET By Text
    @GetMapping("/todo/search")
    public String getTodoByName(@RequestParam(defaultValue = "cat") String name) {
        return "Param is " + name;
    }

    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo) {
        todos.add(new Todo(counter.getAndIncrement(), todo.getName()));
    }

    @PutMapping("/todo/{id}")
    public void editTodo(@RequestBody Todo todo, @PathVariable long id) {
        todos.stream().filter(result -> result.getId() == id)
                .findFirst()
                .ifPresentOrElse(result -> {
                    result.setName(todo.getName());
                }, () -> {
//                    exception
                });
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable long id) {
        todos.stream().filter(result -> result.getId() == id)
                .findFirst()
                .ifPresentOrElse(result -> {
                    todos.remove(result);
                }, () -> {
//                    exception
                });
    }
}
