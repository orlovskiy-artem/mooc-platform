package com.orlovsky.mooc_platform.repository;

import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.StudentProgressItem;
import com.orlovsky.mooc_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentProgressItemRepository extends JpaRepository<StudentProgressItem, Long> {
    List<StudentProgressItem> findByCourseAndStudent(Course course, User student);

    @Query(value = "SELECT step_position FROM student_progress_items spi" +
            " WHERE course_id=?1 AND student_id=?2 ORDER BY step_position DESC LIMIT 1",
            nativeQuery = true)
    Integer findLastStepPosition(Long courseId, Long studentId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM student_progress_items spi" +
            " WHERE course_id=?1 AND student_id=?2",
            nativeQuery = true)
    void deleteByCourseAndStudent(Long courseId, Long studentId);
}
