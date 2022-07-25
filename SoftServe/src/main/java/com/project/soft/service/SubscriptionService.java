package com.project.soft.service;

import com.project.soft.dao.SubscriptionRepository;
import com.project.soft.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;

    public void save(Subscription subscription) {
        if (subscription == null)
            throw new IllegalArgumentException("Subscription can't be null");

        repository.save(subscription);
    }

    public List<Subscription> findAllSubscriptions() {
        return repository.findAll();
    }

    public void removeById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Subscription id can't be null");

        repository.removeById(id);
    }

    public List<Subscription> findALlByUserId(Long id) {
        if (id == null)
            throw new IllegalArgumentException("User id can't be null");

        return repository.findALlByUserId(id);
    }
}
