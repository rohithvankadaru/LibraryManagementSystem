package com.example.LibraryManagementSystem.DTO.ResponseDto;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {

    private int id;

    private Date issueDate;

    private Date updatedOn;

    private CardStatus cardStatus;

    private Date validTill;
}
