package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Book book) throws Exception {
        String msg = bookService.add(book);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PostMapping("/check/{bookTitle}")
    public ResponseEntity<String> check(@PathVariable("bookTitle") String bookTitle) throws Exception {
        String msg = bookService.BookAvailability(bookTitle);
        return new  ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
