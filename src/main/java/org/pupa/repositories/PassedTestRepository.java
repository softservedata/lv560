package org.pupa.repositories;

import org.pupa.models.PassedTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassedTestRepository extends JpaRepository<PassedTest, Long> {
}
