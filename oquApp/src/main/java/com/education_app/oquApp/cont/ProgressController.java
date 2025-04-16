package com.education_app.oquApp.cont;


import com.education_app.oquApp.model.Progress;
import com.education_app.oquApp.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresses")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping
    public List<Progress> getAllProgresses() {
        return progressService.getAllProgresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Progress> getProgressById(@PathVariable Long id) {
        return progressService.getProgressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Progress createProgress(@RequestBody Progress progress) {
        return progressService.createProgress(progress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Progress> updateProgress(
            @PathVariable Long id,
            @RequestBody Progress progressDetails) {
        try {
            Progress updatedProgress = progressService.updateProgress(id, progressDetails);
            return ResponseEntity.ok(updatedProgress);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }

    // Дополнительные endpoints
    @GetMapping("/user/{userId}")
    public List<Progress> getProgressesByUser(@PathVariable Long userId) {
        return progressService.getProgressesByUserId(userId);
    }

    @GetMapping("/course/{courseId}")
    public List<Progress> getProgressesByCourse(@PathVariable Long courseId) {
        return progressService.getProgressesByCourseId(courseId);
    }

    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<Progress> getProgressByUserAndCourse(
            @PathVariable Long userId,
            @PathVariable Long courseId) {
        return progressService.getProgressByUserAndCourse(userId, courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}