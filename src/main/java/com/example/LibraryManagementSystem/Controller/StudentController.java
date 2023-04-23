package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.RequestDto.StudentRequestAddDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.StudentUpdateMobRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentEmailUpdateResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentUpdateMobResponseDto;
import com.example.LibraryManagementSystem.Exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/addViaBody")
    public ResponseEntity<String> add(@RequestBody StudentRequestAddDto studentRequestAddDto) {
        System.out.println(studentRequestAddDto.getEmail());
        String msg = studentService.add(studentRequestAddDto);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDto>> getAll() throws Exception {
        List<StudentResponseDto> list = studentService.getAll();
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @GetMapping("/getById")
    public ResponseEntity<StudentResponseDto> getByid(@RequestParam("id") int id){
        StudentResponseDto studentResponseDto = studentService.getById(id);
        return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
    }


    @PutMapping("/updateMob")
    public ResponseEntity<StudentUpdateMobResponseDto> updateMob(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException {
        StudentUpdateMobResponseDto studentUpdateMobResponseDto = studentService.updateMob(studentUpdateMobRequestDto);
        return new ResponseEntity<>(studentUpdateMobResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> delete(@RequestParam("id") int id) {
        String msg = studentService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<StudentEmailUpdateResponseDto> updateEmail(@RequestParam("id") int id, @RequestParam("eMail") String Email) throws Exception {
        StudentEmailUpdateResponseDto studentEmailUpdateResponseDto = studentService.updateEmail(id, Email);
        return new ResponseEntity<>(studentEmailUpdateResponseDto, HttpStatus.CREATED);
    }

}
