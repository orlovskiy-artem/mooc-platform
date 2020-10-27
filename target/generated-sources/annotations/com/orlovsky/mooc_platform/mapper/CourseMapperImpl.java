package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.CourseDTO;
import com.orlovsky.mooc_platform.model.Author;
import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.EducationalStep;
import com.orlovsky.mooc_platform.model.TestStep;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T20:05:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO toDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        List<EducationalStep> list = course.getEducationalSteps();
        if ( list != null ) {
            courseDTO.setEducationalSteps( new ArrayList<EducationalStep>( list ) );
        }
        courseDTO.setNumberOfSteps( course.getNumberOfSteps() );
        courseDTO.setPrice( course.getPrice() );
        courseDTO.setDescription( course.getDescription() );
        List<TestStep> list1 = course.getTestSteps();
        if ( list1 != null ) {
            courseDTO.setTestSteps( new ArrayList<TestStep>( list1 ) );
        }
        courseDTO.setTitle( course.getTitle() );
        List<Author> list2 = course.getAuthors();
        if ( list2 != null ) {
            courseDTO.setAuthors( new ArrayList<Author>( list2 ) );
        }
        courseDTO.setStatus( course.getStatus() );
        courseDTO.setId( course.getId() );
        courseDTO.setDuration( course.getDuration() );

        return courseDTO;
    }

    @Override
    public Course toEntity(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        Course course = new Course();

        List<EducationalStep> list = courseDTO.getEducationalSteps();
        if ( list != null ) {
            course.setEducationalSteps( new ArrayList<EducationalStep>( list ) );
        }
        course.setNumberOfSteps( courseDTO.getNumberOfSteps() );
        course.setPrice( courseDTO.getPrice() );
        course.setDescription( courseDTO.getDescription() );
        List<TestStep> list1 = courseDTO.getTestSteps();
        if ( list1 != null ) {
            course.setTestSteps( new ArrayList<TestStep>( list1 ) );
        }
        course.setTitle( courseDTO.getTitle() );
        List<Author> list2 = courseDTO.getAuthors();
        if ( list2 != null ) {
            course.setAuthors( new ArrayList<Author>( list2 ) );
        }
        course.setStatus( courseDTO.getStatus() );
        course.setId( courseDTO.getId() );
        course.setDuration( courseDTO.getDuration() );

        return course;
    }

    @Override
    public List<CourseDTO> toDtos(List<Course> courses) {
        if ( courses == null ) {
            return null;
        }

        List<CourseDTO> list = new ArrayList<CourseDTO>( courses.size() );
        for ( Course course : courses ) {
            list.add( toDto( course ) );
        }

        return list;
    }

    @Override
    public List<Course> toEntities(List<CourseDTO> courseDtos) {
        if ( courseDtos == null ) {
            return null;
        }

        List<Course> list = new ArrayList<Course>( courseDtos.size() );
        for ( CourseDTO courseDTO : courseDtos ) {
            list.add( toEntity( courseDTO ) );
        }

        return list;
    }
}
