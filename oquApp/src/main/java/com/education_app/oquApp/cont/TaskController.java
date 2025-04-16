package com.education_app.oquApp.cont;

import com.education_app.oquApp.model.Task;
import com.education_app.oquApp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Task>> getTasksByGroup(@PathVariable Long groupId) {
        List<Task> tasks = taskService.getTasksByGroupId(groupId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Task>> getTasksByCourse(@PathVariable Long courseId) {
        List<Task> tasks = taskService.getTasksByCourseId(courseId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/deadline/before/{deadline}")
    public ResponseEntity<List<Task>> getTasksWithDeadlineBefore(@PathVariable LocalDateTime deadline) {
        List<Task> tasks = taskService.getTasksWithDeadlineBefore(deadline);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}