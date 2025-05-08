// service/TaskService.java
package com.todoapp.todo.service;

import com.todoapp.todo.model.Task;
import com.todoapp.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> getAllTasksSortedByDueDate() {
        List<Task> tasks = repo.findAll();
        tasks.sort(Comparator.comparing(Task::getDueDate));
        return tasks;
    }

    public List<Task> getTasksByCompleted(boolean completed) {
        List<Task> tasks = repo.findByCompleted(completed);
        tasks.sort(Comparator.comparing(Task::getDueDate));
        return tasks;
    }

    public Task addTask(Task task) {
        return repo.save(task);
    }

    public void deleteTask(Long id) {
        repo.deleteById(id);
    }

    public Task markAsCompleted(Long id) {
        Task task = repo.findById(id).orElseThrow();
        task.setCompleted(true);
        return repo.save(task);
    }
}
