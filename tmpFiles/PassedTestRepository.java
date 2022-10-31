package org.pupa.repositories;

import org.pupa.models.PassedTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassedTestRepository extends JpaRepository<PassedTest, Long> {
}
