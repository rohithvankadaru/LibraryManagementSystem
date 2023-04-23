package com.example.LibraryManagementSystem.Service.impl;

import com.example.LibraryManagementSystem.DTO.RequestDto.StudentRequestAddDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.StudentUpdateMobRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.CardResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentEmailUpdateResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentUpdateMobResponseDto;
import com.example.LibraryManagementSystem.Entity.Card;
import com.example.LibraryManagementSystem.Entity.Student;
import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import com.example.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String add(StudentRequestAddDto studentRequestAddDto){

        Student student = new Student();
        student.setName(studentRequestAddDto.getName());
        student.setAge(studentRequestAddDto.getAge());
        student.setDepartment(studentRequestAddDto.getDepartment());
        student.setMobNo(studentRequestAddDto.getMobNo());
        student.setEmail(studentRequestAddDto.getEmail());

        Card card = new Card(CardStatus.ACTIVE, student);
        Date date = card.getIssueDate();
        date = new Date(date.getYear()+4, date.getMonth(), date.getDate());
        card.setValidTill(date);
        student.setCard(card);
        studentRepository.save(student);
        return "student added successfully";
    }

    public List<StudentResponseDto> getAll() {
        List<Student> allStudents= studentRepository.findAll();
        List<StudentResponseDto> studentResponseDtoList = new ArrayList<>();
        for(Student student : allStudents){
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto.setId(student.getId());
            studentResponseDto.setName(student.getName());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setMobNo(student.getMobNo());
            Card card = student.getCard();
            CardResponseDto cardResponseDto = new CardResponseDto(card.getId(), card.getIssueDate(), card.getUpdatedOn(), card.getCardStatus(), card.getValidTill());
            studentResponseDto.setCardResponseDto(cardResponseDto);
            studentResponseDtoList.add(studentResponseDto);
        }
        return studentResponseDtoList;
    }



    public String delete(int id){
        studentRepository.deleteById(id);
        return "deletion done";
    }

    @Override
    public StudentUpdateMobResponseDto updateMob(StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException {
        Student student;
        try{
            student = studentRepository.findById(studentUpdateMobRequestDto.getId()).get();
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid StudentId");
        }
        student.setMobNo(studentUpdateMobRequestDto.getMobNo());
        studentRepository.save(student);
        StudentUpdateMobResponseDto studentUpdateMobResponseDto = new StudentUpdateMobResponseDto();
        studentUpdateMobResponseDto.setName(student.getName());
        studentUpdateMobResponseDto.setMobNo(student.getMobNo());
        return studentUpdateMobResponseDto;
    }

    @Override
    public StudentResponseDto getById(int id) {
        Student student = studentRepository.findById(id).get();
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setDepartment(student.getDepartment());
        studentResponseDto.setMobNo(student.getMobNo());
        Card card = student.getCard();
        CardResponseDto cardResponseDto = new CardResponseDto(card.getId(), card.getIssueDate(), card.getUpdatedOn(), card.getCardStatus(), card.getValidTill());
        studentResponseDto.setCardResponseDto(cardResponseDto);
        return studentResponseDto;
    }

    @Override
    public StudentEmailUpdateResponseDto updateEmail(int studentId, String Email) throws Exception {
        Student student;
        try {
            student = studentRepository.findById(studentId).get();
        }
        catch (Exception e){
            throw new Exception("Invalid Student");
        }

        student.setEmail(Email);

        studentRepository.save(student);

        StudentEmailUpdateResponseDto studentEmailUpdateResponseDto = new StudentEmailUpdateResponseDto(student.getName(), Email);
        return studentEmailUpdateResponseDto;
    }

}
