package com.orlovsky.mooc_platform.repository;

import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM courses c\n" +
            "    INNER JOIN course_student cs ON cs.сourse_id=c.id\n" +
            "    WHERE cs.student_id=?1",
            nativeQuery=true)
    Collection<Course> findCoursesByStudentsIdsIn(Long studentsId);

//    Collection<Course> findCoursesByStudentsIn(List<User> students);

    @Query(value = "SELECT * FROM courses c\n" +
            "    INNER JOIN course_author ca ON ca.сourse_id=c.id\n" +
            "    WHERE ca.author_id=?1",
            nativeQuery=true)
    Collection<Course> findCourseByAuthor(Long authorId);


//    SEARCH (filter)
    @Query("SELECT c FROM Course c" +
            " WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', ?1,'%'))" +
            " OR LOWER(c.description) LIKE LOWER(CONCAT('%', ?1,'%'))")
    List<Course> search(String keyword);

}
