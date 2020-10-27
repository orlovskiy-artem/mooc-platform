package com.orlovsky.mooc_platform.service;

import com.orlovsky.mooc_platform.dto.AuthorDTO;
import com.orlovsky.mooc_platform.dto.StudentDTO;
import com.orlovsky.mooc_platform.model.Student;
import com.orlovsky.mooc_platform.model.Author;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Student signUpStudent(StudentDTO studentDTO);

    Author signUpAuthor(AuthorDTO authorDTO);

    Student getStudentById(UUID studentId);

    Author getAuthorById(UUID authorId);

    List<Student> getAllStudents();

    List<Author> getAllAuthors();

    void updateStudent(UUID studentId,StudentDTO studentDTO);

    void updateAuthor(UUID authorId, AuthorDTO authorDTO);

    void deleteStudentById(UUID studentId);

    void deleteAuthorById(UUID authorId);


}
