package com.example.LibraryManagementSystem.Service.impl;

import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String add(Book book) throws Exception {
        int authorId = book.getAuthor().getId();

        Author author;
        try{
            author = authorRepository.findById(authorId).get();
        }
        catch (Exception e){
            return "no such author";
        }
        author.getBooks().add(book);
        book.setAuthor(author);
        authorRepository.save(author);
        return "Book added successfully";
    }
}
