package com.orlovsky.mooc_platform.repository;

import com.orlovsky.mooc_platform.model.TestStepOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestStepOptionRepository extends JpaRepository<TestStepOption, Long> {
}
