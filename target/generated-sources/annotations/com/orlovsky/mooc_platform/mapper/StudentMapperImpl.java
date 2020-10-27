package com.orlovsky.mooc_platform.mapper;

import com.orlovsky.mooc_platform.dto.StudentDTO;
import com.orlovsky.mooc_platform.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T20:05:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO toDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setFirstName( student.getFirstName() );
        studentDTO.setLastName( student.getLastName() );
        studentDTO.setDescription( student.getDescription() );
        studentDTO.setId( student.getId() );

        return studentDTO;
    }

    @Override
    public Student toEntity(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setFirstName( studentDTO.getFirstName() );
        student.setLastName( studentDTO.getLastName() );
        student.setDescription( studentDTO.getDescription() );
        student.setId( studentDTO.getId() );

        return student;
    }

    @Override
    public List<StudentDTO> toDtos(List<Student> students) {
        if ( students == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>( students.size() );
        for ( Student student : students ) {
            list.add( toDto( student ) );
        }

        return list;
    }

    @Override
    public List<Student> toEntities(List<StudentDTO> studentDTOs) {
        if ( studentDTOs == null ) {
            return null;
        }

        List<Student> list = new ArrayList<Student>( studentDTOs.size() );
        for ( StudentDTO studentDTO : studentDTOs ) {
            list.add( toEntity( studentDTO ) );
        }

        return list;
    }
}
