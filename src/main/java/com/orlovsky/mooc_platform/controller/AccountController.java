package com.orlovsky.mooc_platform.controller;

import com.orlovsky.mooc_platform.dto.AuthorDTO;
import com.orlovsky.mooc_platform.dto.StudentDTO;
import com.orlovsky.mooc_platform.dto.UserDTO;
import com.orlovsky.mooc_platform.mapper.AuthorMapper;
import com.orlovsky.mooc_platform.mapper.StudentMapper;
import com.orlovsky.mooc_platform.model.Author;
import com.orlovsky.mooc_platform.model.Student;
import com.orlovsky.mooc_platform.model.User;
import com.orlovsky.mooc_platform.service.AccountService;
import com.orlovsky.mooc_platform.service.impl.UserService;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import java.util.MissingResourceException;
import java.util.UUID;

@RestController
public class AccountController {
    @Autowired
    private UserService accountService;

    //TODO REST API (to redo)

//    @GetMapping("/user/registration")
//    public String showRegistrationForm(WebRequest request, Model model) {
//        UserDTO userDto = new UserDTO();
//        model.addAttribute("user", userDto);
//        return "registration";
//    }

//    // CRUD for students
//    @PostMapping(value = "/users")
//    public ResponseEntity<User> createStudent(@RequestBody UserDTO body){
//        User user = accountService.signUpUser(body);
//        return new ResponseEntity<>(user,HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/users")
//    public ResponseEntity<?> getAllUsers(){
//       List<StudentDTO> studentDTOs = StudentMapper.INSTANCE.
//               toDtos(accountService.getAllStudents());
//        return new ResponseEntity<>(studentDTOs,HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/students/{id}")
//    public ResponseEntity<?> getStudentById(@PathVariable(name = "id") UUID id){
//        try{
//           Student student = accountService.getStudentById(id);
//           StudentDTO body = StudentMapper.INSTANCE.toDto(student);
//           return new ResponseEntity<>(body, HttpStatus.OK);
//        } catch (MissingResourceException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping(value = "/students/{id}")
//    public ResponseEntity<?> updateStudentById(@PathVariable(name = "id") UUID id,
//                                               @RequestBody StudentDTO body) {
//        try{
//            accountService.updateStudent(id,body);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (MissingResourceException e){
//            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//        }
//    }
//
//    @DeleteMapping(value = "/students/{id}")
//    public ResponseEntity<?> deleteStudentById(@PathVariable(name = "id") UUID id){
//        accountService.deleteStudentById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    // CRUD for authors
//    @PostMapping(value = "/authors")
//    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO body){
//        Author author = accountService.signUpAuthor(body);
//        return new ResponseEntity<>(author,HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/authors")
//    public ResponseEntity<?> getAllAuthors(){
//        List<AuthorDTO> authorDTOs = AuthorMapper.INSTANCE.
//                toDtos(accountService.getAllAuthors());
//        return new ResponseEntity<>(authorDTOs,HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/authors/{id}")
//    public ResponseEntity<?> getAuthorById(@PathVariable(name = "id") UUID id){
//        try{
//            Author author = accountService.getAuthorById(id);
//            AuthorDTO body = AuthorMapper.INSTANCE.toDto(author);
//            return new ResponseEntity<>(body, HttpStatus.OK);
//        } catch (MissingResourceException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping(value = "/authors/{id}")
//    public ResponseEntity<?> updateAuthor(@PathVariable(name = "id") UUID id,
//                                           @RequestBody AuthorDTO body){
//        try{
//            accountService.updateAuthor(id,body);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (MissingResourceException e){
//            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//        }
//    }
//
//    @DeleteMapping(value = "/authors/{id}")
//    public ResponseEntity<?> deleteAuthorById(@PathVariable(name = "id") UUID id){
//        accountService.deleteAuthorById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
