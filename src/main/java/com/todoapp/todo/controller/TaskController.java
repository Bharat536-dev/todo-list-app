// controller/TaskController.java
package com.todoapp.todo.controller;

import com.todoapp.todo.model.Task;
import com.todoapp.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll(@RequestParam(required = false) String filter) {
        if ("completed".equalsIgnoreCase(filter)) {
            return service.getTasksByCompleted(true);
        } else if ("pending".equalsIgnoreCase(filter)) {
            return service.getTasksByCompleted(false);
        } else {
            return service.getAllTasksSortedByDueDate();
        }
    }

    @PostMapping
    public Task add(@RequestBody Task task) {
        return service.addTask(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTask(id);
    }

    @PutMapping("/{id}/complete")
    public Task complete(@PathVariable Long id) {
        return service.markAsCompleted(id);
    }
}
