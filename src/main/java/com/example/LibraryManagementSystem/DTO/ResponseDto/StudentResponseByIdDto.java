package com.example.LibraryManagementSystem.DTO.ResponseDto;

import com.example.LibraryManagementSystem.Enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponseByIdDto {

    private int id;

    private String name;

    private int age;

    private Department department;

    private String mobNo;
}
