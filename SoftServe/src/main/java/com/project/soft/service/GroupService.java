package com.project.soft.service;

import com.project.soft.dao.GroupRepository;
import com.project.soft.entity.Group;
import com.project.soft.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class GroupService {

    private final GroupRepository repository;
    private final TestService testService;

    public void save(Group group) {
        if (group == null)
            throw new IllegalArgumentException("Group can't be null");

        repository.save(group);
    }

    public List<Group> findAllGroups() {
        return repository.findAll();
    }

    public Group findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Group id can't be null");

        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("There's no group with id '%d'", id))
        );
    }

    public void updateTests(Long groupId, List<Long> testsIds) {
        Group group = repository.findById(groupId).orElseThrow(() ->
                new IllegalArgumentException(String.format("There's no group with id '%d'", groupId))
        );

        Set<Long> oldTestsIds = group.getTests().stream()
                .map(Test::getId)
                .collect(Collectors.toSet());

        for (Long testId: testsIds) {
            if (!oldTestsIds.contains(testId)) {
                Test test = testService.findTestById(testId);
                group.addTest(test);
            }
        }

        for (Long testId: oldTestsIds) {
            if (!testsIds.contains(testId)) {
                Test test = testService.findTestById(testId);
                group.removeTest(test);
            }
        }
    }

    public void removeById(Long id){
        if (id == null)
            throw new IllegalArgumentException("Group id can't be null");

        repository.removeById(id);
    }
}
