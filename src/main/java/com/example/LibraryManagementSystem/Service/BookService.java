package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.Entity.Book;

import java.util.List;

public interface BookService {
    public String add(Book book) throws Exception;

    public String BookAvailability(String title) throws Exception;

    public List<Book> books(String title);
}
