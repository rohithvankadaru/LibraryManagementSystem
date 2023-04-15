package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.RequestDto.BookIssueRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.BookIssueResponseDto;
import com.example.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public BookIssueResponseDto issue(@RequestBody BookIssueRequestDto bookIssueRequestDto) throws Exception {
       return transactionService.issueBook(bookIssueRequestDto);
    }
}
