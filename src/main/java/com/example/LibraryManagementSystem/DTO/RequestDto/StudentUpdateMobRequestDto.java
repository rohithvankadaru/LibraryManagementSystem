package com.example.LibraryManagementSystem.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateMobRequestDto {
    int id;

    String mobNo;
}
