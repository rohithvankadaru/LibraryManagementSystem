package com.example.LibraryManagementSystem.Service.impl;

import com.example.LibraryManagementSystem.DTO.ResponseDto.AuthorResponseDto;
import com.example.LibraryManagementSystem.Entity.Author;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import com.example.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String add(Author author) {
        authorRepository.save(author);
        return "Author added successfully";
    }

    @Override
    public String delete(int id) {
        authorRepository.deleteById(id);
        return "deleted author";
    }

    @Override
    public List<AuthorResponseDto> getByAge(int age) {
        List<Author> authors = authorRepository.findByAge(age);
        List<AuthorResponseDto> ansList = new ArrayList<>();
        for(Author author : authors){
            ansList.add(new AuthorResponseDto(author.getId(), author.getName(), author.getAge(), author.getEmail()));
        }
        return ansList;
    }

    @Override
    public String updateEmail(String Email, int id) {
        Author author;
        try {
            author = authorRepository.findById(id).get();
        }
        catch (Exception e){
            return "Invalid id..!!";
        }
        author.setEmail(Email);
        authorRepository.save(author);
        return "Email updated..!!";
    }
}
