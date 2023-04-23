package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.RequestDto.BookAddRequestDto;
import com.example.LibraryManagementSystem.Entity.Book;

import java.util.List;

public interface BookService {
    public String add(BookAddRequestDto bookAddRequestDto) throws Exception;

    public String BookAvailability(String title) throws Exception;

    public List<Book> books(String title);
}
