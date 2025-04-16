package com.education_app.oquApp.repo;

import com.education_app.oquApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.user.user_id = :userId")
    List<Task> findByUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE t.group.id = :groupId")
    List<Task> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT t FROM Task t WHERE t.course.id = :courseId")
    List<Task> findByCourseId(@Param("courseId") Long courseId);

    List<Task> findByDeadlineBefore(LocalDateTime deadline);
}