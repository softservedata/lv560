package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Test;
import com.project.soft.entity.Topic;

import java.util.List;
import java.util.Set;

public interface TopicRepository extends SoftRepository<Topic, Long> {

    List<Topic> findALlByTitles(List<String> topicsTitle);
}
