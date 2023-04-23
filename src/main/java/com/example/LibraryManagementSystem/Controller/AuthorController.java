package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.ResponseDto.AuthorResponseDto;
import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Author author){
        String msg = authorService.add(author);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping("/updateEmail/{id}/{Email}")
    public ResponseEntity<String> updateMail(@PathVariable("id") int id,@PathVariable("Email") String Email){
        String msg = authorService.updateEmail(Email, id);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") int id){
        String msg = authorService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/getByAge")
    public ResponseEntity<List<AuthorResponseDto>> getByAge(@RequestParam("age") int age){
        List<AuthorResponseDto> list= authorService.getByAge(age);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }
}
