package com.education_app.oquApp.repo;

import com.education_app.oquApp.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query("SELECT s FROM Submission s WHERE s.user.user_id = :userId")
    List<Submission> findByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM Submission s WHERE s.task.id = :taskId")
    List<Submission> findByTaskId(@Param("taskId") Long taskId);
}