package com.example.LibraryManagementSystem.DTO.RequestDto;

import com.example.LibraryManagementSystem.Enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestAddDto {

    private String name;

    private int age;

    private Department department;

    private String email;

    private String mobNo;

}
