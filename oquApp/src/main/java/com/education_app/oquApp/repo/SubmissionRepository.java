package com.education_app.oquApp.repo;

import com.education_app.oquApp.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

}
