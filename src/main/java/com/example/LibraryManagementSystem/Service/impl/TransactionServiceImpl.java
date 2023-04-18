package com.example.LibraryManagementSystem.Service.impl;

import com.example.LibraryManagementSystem.DTO.RequestDto.BookIssueRequestDto;
import com.example.LibraryManagementSystem.DTO.RequestDto.BookReturnRequestDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.BookIssueResponseDto;
import com.example.LibraryManagementSystem.DTO.ResponseDto.BookReturnResponseDto;
import com.example.LibraryManagementSystem.Entity.Book;
import com.example.LibraryManagementSystem.Entity.Card;
import com.example.LibraryManagementSystem.Entity.Transaction;
import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Enums.TransactionType;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Repository.CardRepository;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import com.example.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public BookIssueResponseDto issueBook(BookIssueRequestDto bookIssueRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(UUID.randomUUID());
        transaction.setTransactionType(TransactionType.ISSUE);

        Card card;
         try {
             card = cardRepository.findById(bookIssueRequestDto.getCardId()).get();
         }
         catch (Exception e){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);
             throw new Exception("Invalid card id!!!");
         }

        transaction.setCard(card);

        Book book;
         try {
            book = bookRepository.findById(bookIssueRequestDto.getBookId()).get();
         }
         catch (Exception e){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);
             throw new Exception("Invalid book id!!!");
         }

         transaction.setBook(book);

         if(card.getCardStatus() != CardStatus.ACTIVE){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);
             throw new Exception("Card not Active..!!!");
         }

         if(book.isIssued()){
             transaction.setTransactionStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);
             throw new Exception("Book not available right know..!!");
         }

         transaction.setTransactionStatus(TransactionStatus.SUCCESS);
         book.setIssued(true);
         book.setCard(card);
         book.getTransactions().add(transaction);

         card.getBooks().add(book);
         card.getTransaction().add(transaction);

         cardRepository.save(card);

         BookIssueResponseDto bookIssueResponseDto = new BookIssueResponseDto(transaction.getTransactionNumber(), transaction.getTransactionStatus(), book.getTitle());
         return bookIssueResponseDto;
    }

    @Override
    public BookReturnResponseDto returnBook(BookReturnRequestDto bookReturnRequestDto) throws Exception {

        Transaction transaction = new Transaction();

        transaction.setTransactionNumber(UUID.randomUUID());

        transaction.setTransactionType(TransactionType.RETURN);

        Card card;
        try{
            card = cardRepository.findById(bookReturnRequestDto.getCardId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("invalid cardId...!!!");
        }
        transaction.setCard(card);

        Book book;

        try {
            book = bookRepository.findById(bookReturnRequestDto.getBookId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("invalid Book...!!!");
        }

        transaction.setBook(book);

        if(card.getCardStatus() != CardStatus.ACTIVE){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Check the card status");
        }

        if(!book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("get cleared with bookId, Book not even issued");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(false);
        book.setCard(null);
        book.getTransactions().add(transaction);

        card.getBooks().add(book);
        card.getTransaction().add(transaction);

        cardRepository.save(card);

        BookReturnResponseDto bookReturnResponseDto = new BookReturnResponseDto(book.getTitle(), transaction.getTransactionStatus());

        return bookReturnResponseDto;
    }
}

















