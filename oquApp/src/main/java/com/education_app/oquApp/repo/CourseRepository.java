package com.education_app.oquApp.repo;

import com.education_app.oquApp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
