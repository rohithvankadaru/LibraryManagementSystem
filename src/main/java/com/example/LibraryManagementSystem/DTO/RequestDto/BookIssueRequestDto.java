package com.example.LibraryManagementSystem.DTO.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookIssueRequestDto {

    private int cardId;

    private int bookId;
}
