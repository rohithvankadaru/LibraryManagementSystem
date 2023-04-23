package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.RequestDto.StudentRequestAddDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.StudentUpdateMobRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentEmailUpdateResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentUpdateMobResponseDto;
import com.example.LibraryManagementSystem.Exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    public String add(StudentRequestAddDto studentRequestAddDto);
    public List<StudentResponseDto> getAll() throws Exception;
    public String delete(int id);

    public StudentUpdateMobResponseDto updateMob(StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException;

    public StudentResponseDto getById(int id);

    public StudentEmailUpdateResponseDto updateEmail(int studentId, String Email) throws Exception;
}
