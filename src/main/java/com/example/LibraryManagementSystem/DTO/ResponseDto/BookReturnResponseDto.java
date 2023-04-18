package com.example.LibraryManagementSystem.DTO.ResponseDto;


import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReturnResponseDto {
    String bookTitle;

    TransactionStatus transactionStatus;
}
