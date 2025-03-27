package com.education_app.oquApp.repo;

import com.education_app.oquApp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
