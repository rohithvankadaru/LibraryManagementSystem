package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.DTO.ResponseDto.CardResponseDto;
import com.example.LibraryManagementSystem.Entity.Card;
import com.example.LibraryManagementSystem.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardRepository cardRepository;

    @GetMapping("get/{id}")
    public CardResponseDto getCard(@PathVariable("id") int id){
        Card card = cardRepository.getReferenceById(id);
        CardResponseDto cardResponseDto = new CardResponseDto(card.getId(), card.getIssueDate(),card.getUpdatedOn(), card.getCardStatus(),card.getValidTill());
        return cardResponseDto;
    }
}
