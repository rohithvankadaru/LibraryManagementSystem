package com.example.LibraryManagementSystem.DTO.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorResponseDto {

    private int id;

    private String name;

    private int age;

    private String email;
}
