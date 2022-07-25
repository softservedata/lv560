package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Subscription;

import java.util.List;

public interface SubscriptionRepository extends SoftRepository<Subscription, Long> {

    List<Subscription> findALlByUserId(Long id);
}
