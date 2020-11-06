package com.orlovsky.mooc_platform.repository;

import com.orlovsky.mooc_platform.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// Will be removed
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>{
}
