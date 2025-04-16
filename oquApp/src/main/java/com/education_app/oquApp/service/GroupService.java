package com.education_app.oquApp.service;

import com.education_app.oquApp.model.Group;
import com.education_app.oquApp.repo.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    public List<Group> getGroupsByCourseId(Long courseId) {
        return groupRepository.findByCourseId(courseId);
    }

    @Transactional
    public Group updateGroup(Long id, Group groupDetails) {
        return groupRepository.findById(id)
                .map(group -> {
                    group.setName(groupDetails.getName());
                    // Don't update course as it's a foreign key
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
    }

    @Transactional
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
}