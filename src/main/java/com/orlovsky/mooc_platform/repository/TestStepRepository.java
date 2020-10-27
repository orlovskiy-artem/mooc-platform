package com.orlovsky.mooc_platform.repository;

import com.orlovsky.mooc_platform.model.TestStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestStepRepository extends JpaRepository<TestStep, UUID> {
}
