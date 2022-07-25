package com.project.soft.service;

import com.project.soft.dao.TopicRepository;
import com.project.soft.entity.Test;
import com.project.soft.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TopicService {

    private final TopicRepository repository;

    public void updateTopics(List<Long> topicIds, Test test) {
        if (topicIds == null) return;

        List<Topic> topics = repository.findALlByIds(topicIds);
        test.addTopics(topics);
    }

    public void save(Topic topic) {
        repository.save(topic);
    }

    public List<Topic> findAllTopics() {
        return repository.findAll();
    }

    public Topic findById(Long id){

        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("There's no topic with id '%d'", id))
        );
    }
}
