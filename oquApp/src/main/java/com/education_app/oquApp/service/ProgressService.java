package com.education_app.oquApp.service;
import com.education_app.oquApp.model.Progress;
import com.education_app.oquApp.repo.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProgressService {

    private final ProgressRepository progressRepository;

    @Autowired
    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public List<Progress> getAllProgresses() {
        return progressRepository.findAll();
    }

    public Optional<Progress> getProgressById(Long id) {
        return progressRepository.findById(id);
    }

    public Progress createProgress(Progress progress) {
        calculateAndSetAvgGrade(progress);
        return progressRepository.save(progress);
    }

    public Progress updateProgress(Long id, Progress progressDetails) {
        Progress existingProgress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found with id: " + id));

        existingProgress.setTotal_tasks(progressDetails.getTotal_tasks());
        existingProgress.setCompleted_tasks(progressDetails.getCompleted_tasks());
        calculateAndSetAvgGrade(existingProgress);

        return progressRepository.save(existingProgress);
    }

    public void deleteProgress(Long id) {
        progressRepository.deleteById(id);
    }

    private void calculateAndSetAvgGrade(Progress progress) {
        double avgGrade = progress.getTotal_tasks() > 0
                ? (progress.getCompleted_tasks() * 100.0) / progress.getTotal_tasks()
                : 0.0;
        progress.setAvg_grade(avgGrade);
    }

    // Дополнительные методы
    public List<Progress> getProgressesByUserId(Long userId) {
        return progressRepository.findByUserId(userId);
    }

    public List<Progress> getProgressesByCourseId(Long courseId) {
        return progressRepository.findByCourseId(courseId);
    }

    public Optional<Progress> getProgressByUserAndCourse(Long userId, Long courseId) {
        return progressRepository.findByUserAndCourse(userId, courseId);
    }
}