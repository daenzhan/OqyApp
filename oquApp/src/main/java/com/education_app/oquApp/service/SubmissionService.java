package com.education_app.oquApp.service;
import com.education_app.oquApp.model.Submission;
import com.education_app.oquApp.repo.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Transactional
    public Submission createSubmission(Submission submission) {
        submission.setTime(LocalDateTime.now());
        return submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    public List<Submission> getSubmissionsByUserId(Long userId) {
        return submissionRepository.findByUserId(userId);
    }

    public List<Submission> getSubmissionsByTaskId(Long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    @Transactional
    public Submission updateSubmission(Long id, Submission submissionDetails) {
        return submissionRepository.findById(id)
                .map(submission -> {
                    submission.setScore(submissionDetails.getScore());
                    submission.setStatus(submissionDetails.getStatus());
                    return submissionRepository.save(submission);
                })
                .orElseThrow(() -> new RuntimeException("Submission not found with id: " + id));
    }

    @Transactional
    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }
}