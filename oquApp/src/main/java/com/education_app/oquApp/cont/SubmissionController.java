package com.education_app.oquApp.cont;

import com.education_app.oquApp.model.Submission;
import com.education_app.oquApp.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> createSubmission(@RequestBody Submission submission) {
        Submission createdSubmission = submissionService.createSubmission(submission);
        return ResponseEntity.ok(createdSubmission);
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        List<Submission> submissions = submissionService.getAllSubmissions();
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        return submissionService.getSubmissionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Submission>> getSubmissionsByUser(@PathVariable Long userId) {
        List<Submission> submissions = submissionService.getSubmissionsByUserId(userId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getSubmissionsByTask(@PathVariable Long taskId) {
        List<Submission> submissions = submissionService.getSubmissionsByTaskId(taskId);
        return ResponseEntity.ok(submissions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmission(@PathVariable Long id, @RequestBody Submission submissionDetails) {
        Submission updatedSubmission = submissionService.updateSubmission(id, submissionDetails);
        return ResponseEntity.ok(updatedSubmission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.noContent().build();
    }
}
