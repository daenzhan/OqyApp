package com.education_app.oquApp.repo;

import com.education_app.oquApp.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

}

