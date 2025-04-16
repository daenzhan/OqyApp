package com.education_app.oquApp.service;

import com.education_app.oquApp.model.Task;
import com.education_app.oquApp.repo.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByGroupId(Long groupId) {
        return taskRepository.findByGroupId(groupId);
    }

    public List<Task> getTasksByCourseId(Long courseId) {
        return taskRepository.findByCourseId(courseId);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getTasksWithDeadlineBefore(LocalDateTime deadline) {
        return taskRepository.findByDeadlineBefore(deadline);
    }

    @Transactional
    public Task updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    task.setDeadline(taskDetails.getDeadline());
                    // Don't update relationships as they are foreign keys
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}