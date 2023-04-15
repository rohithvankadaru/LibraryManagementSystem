package com.example.LibraryManagementSystem.Service.impl;

import com.example.LibraryManagementSystem.DTO.RequestDto.StudentRequestAddDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.StudentUpdateMobRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.CardResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.StudentResponseByIdDto;
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

        Card card = new Card(CardStatus.ACTIVE, student);
        Date date = card.getIssueDate();
        date = new Date(date.getYear()+4, date.getMonth(), date.getDate());
        card.setValidTill(date);
        student.setCard(card);
        studentRepository.save(student);
        return "student added successfully";
    }

    public List<StudentResponseByIdDto> getAll() {
        List<Student> allStudents= studentRepository.findAll();
        List<StudentResponseByIdDto> ans = new ArrayList<>();
        for(Student student : allStudents){
            StudentResponseByIdDto replica = new StudentResponseByIdDto();
            replica.setId(student.getId());
            replica.setName(student.getName());
            replica.setAge(student.getAge());
            replica.setDepartment(student.getDepartment());
            replica.setMobNo(student.getMobNo());
            Card card = student.getCard();
            CardResponseDto cardResponseDto = new CardResponseDto(card.getId(), card.getIssueDate(), card.getUpdatedOn(), card.getCardStatus(), card.getValidTill());
            replica.setCardResponseDto(cardResponseDto);
            ans.add(replica);
        }
        return ans;
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
    public StudentResponseByIdDto getById(int id) {
        Student student = studentRepository.findById(id).get();
        StudentResponseByIdDto studentResponseByIdDto = new StudentResponseByIdDto();
        studentResponseByIdDto.setId(student.getId());
        studentResponseByIdDto.setName(student.getName());
        studentResponseByIdDto.setAge(student.getAge());
        studentResponseByIdDto.setDepartment(student.getDepartment());
        studentResponseByIdDto.setMobNo(student.getMobNo());
        Card card = student.getCard();
        CardResponseDto cardResponseDto = new CardResponseDto(card.getId(), card.getIssueDate(), card.getUpdatedOn(), card.getCardStatus(), card.getValidTill());
        studentResponseByIdDto.setCardResponseDto(cardResponseDto);
        return studentResponseByIdDto;
    }

}
