package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.RequestDto.StudentRequestAddDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.StudentUpdateMobRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentResponseByIdDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentUpdateMobResponseDto;
import com.example.LibraryManagementSystem.Entity.Card;
import com.example.LibraryManagementSystem.Exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.Repository.CardRepository;
import com.example.LibraryManagementSystem.Service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping("/addViaBody")
    public String add(@RequestBody StudentRequestAddDto studentRequestAddDto) {
        return studentService.add(studentRequestAddDto);
    }

    @GetMapping("/getAll")
    public List<StudentResponseByIdDto> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/getById")
    public StudentResponseByIdDto getByid(@RequestParam("id") int id){
        return studentService.getById(id);
    }


    @PutMapping("/updateMob")
    public StudentUpdateMobResponseDto updateMob(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException {
        return studentService.updateMob(studentUpdateMobRequestDto);
    }

    @DeleteMapping("/deleteById")
    public String delete(@RequestParam("id") int id) {
        return studentService.delete(id);
    }

}
