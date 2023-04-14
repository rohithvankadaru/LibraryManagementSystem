package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.ResponseDto.AuthorResponseDto;
import com.example.LibraryManagementSystem.Entity.Author;

import java.util.List;

public interface AuthorService {

    public String add(Author author);

    public String delete(int id);

    public List<AuthorResponseDto> getByAge(int age);
}
