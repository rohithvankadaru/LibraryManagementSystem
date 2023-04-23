package com.example.LibraryManagementSystem.Service.impl;

import com.example.LibraryManagementSystem.DTO.RequestDto.BookAddRequestDto;
import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    public String add(BookAddRequestDto bookAddRequestDto) throws Exception {
        int authorId = bookAddRequestDto.getAuthorId();

        Author author;
        try{
            author = authorRepository.findById(authorId).get();
        }
        catch (Exception e){
            return "no such author";
        }

        Book book = new Book();

        book.setTitle(bookAddRequestDto.getTitle());
        book.setGenre(bookAddRequestDto.getGenre());
        book.setPages(bookAddRequestDto.getPages());
        book.setPrice(bookAddRequestDto.getPrice());
        book.setAuthor(author);

        author.getBooks().add(book);
        authorRepository.save(author);
        return "Book added successfully";
    }

    @Override
    public String BookAvailability(String title) throws Exception {
        List<Book> bookList = books(title);
        if(bookList.size() == 0){
            return "no such book exists..!!";
        }

        for( Book book : bookList){
            if(!book.isIssued()) return "Available..!! with BookId: "+book.getId();
        }
        return "Not Available..!!";
    }

    @Override
    public List<Book> books(String title) {
        return bookRepository.getByTitle(title);
    }
}
