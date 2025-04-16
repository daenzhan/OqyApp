package com.education_app.oquApp.repo;
import com.education_app.oquApp.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId")
    List<Progress> findByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Progress p WHERE p.course.id = :courseId")
    List<Progress> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId AND p.course.id = :courseId")
    Optional<Progress> findByUserAndCourse(
            @Param("userId") Long userId,
            @Param("courseId") Long courseId);
}