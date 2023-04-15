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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        return new ResponseEntity<>(authorService.delete(id), HttpStatus.CREATED);
    }

    @GetMapping("/getByAge")
    public ResponseEntity<List<AuthorResponseDto>> getByAge(int age){
        return new ResponseEntity<>(authorService.getByAge(age), HttpStatus.CREATED);
    }
}
