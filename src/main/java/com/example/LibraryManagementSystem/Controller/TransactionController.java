package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.RequestDto.BookIssueRequestDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.BookReturnRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.BookIssueResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.BookReturnResponseDto;
import com.example.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookIssueResponseDto> issue(@RequestBody BookIssueRequestDto bookIssueRequestDto) throws Exception {
       BookIssueResponseDto bookIssueResponseDto = transactionService.issueBook(bookIssueRequestDto);
       return new ResponseEntity<>(bookIssueResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("returnBook")
    public ResponseEntity<BookReturnResponseDto> returnBook(@RequestBody BookReturnRequestDto bookReturnRequestDto) throws Exception {

        BookReturnResponseDto bookReturnResponseDto = transactionService.returnBook(bookReturnRequestDto);
        return new ResponseEntity<>(bookReturnResponseDto,HttpStatus.CREATED);
    }
}
