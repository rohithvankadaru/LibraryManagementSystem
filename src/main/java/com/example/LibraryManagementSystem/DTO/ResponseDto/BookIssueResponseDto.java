package com.example.LibraryManagementSystem.DTO.ResponseDto;

import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookIssueResponseDto {

    private String transactionId;

    private TransactionStatus transactionStatus;

    private String bookName;
}
