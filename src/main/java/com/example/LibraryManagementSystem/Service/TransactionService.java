package com.example.LibraryManagementSystem.Service;

import com.example.LibraryManagementSystem.DTO.RequestDto.BookIssueRequestDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.BookReturnRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.BookIssueResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.BookReturnResponseDto;

public interface TransactionService {
    public BookIssueResponseDto issueBook(BookIssueRequestDto bookIssueRequestDto) throws Exception;

//    public BookReturnResponseDto returnBook(BookReturnRequestDto);
}
