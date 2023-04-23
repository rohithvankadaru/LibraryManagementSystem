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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public BookIssueResponseDto issueBook(BookIssueRequestDto bookIssueRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(UUID.randomUUID().toString());
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

         String text = "Congracts..!! "+card.getStudent().getName() + " you have been issued a book: "+book.getTitle();

         SimpleMailMessage mailMessage = new SimpleMailMessage();
         mailMessage.setFrom("noreplylibrarysystem1@gmail.com");
         mailMessage.setTo(card.getStudent().getEmail());
         mailMessage.setSubject("issue Book");
         mailMessage.setText(text);
         mailSender.send(mailMessage);

         return bookIssueResponseDto;
    }

    @Override
    public BookReturnResponseDto returnBook(BookReturnRequestDto bookReturnRequestDto) throws Exception {

        Transaction transaction = new Transaction();

        transaction.setTransactionNumber(UUID.randomUUID().toString());

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

        Date issueDate = null;
        List<Transaction> transactionList = book.getTransactions();
        for (Transaction transaction1 : transactionList) {
            if(transaction1.getBook() == book) issueDate = transaction1.getTransactionDate();
        }

        Date today = new Date();

        int penality = (today.getDate()-issueDate.getDate()) > 15? today.getDate()-issueDate.getDate() : 0;

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

        BookReturnResponseDto bookReturnResponseDto = new BookReturnResponseDto(book.getTitle(), transaction.getTransactionNumber(), transaction.getTransactionStatus(), penality);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String text = "Good job " +card.getStudent().getName()+" returned book: "+book.getTitle()+"with penality: "+penality;
        mailMessage.setSubject("return Book");
        mailMessage.setText(text);
        mailMessage.setTo(card.getStudent().getEmail());
        mailSender.send(mailMessage);

        return bookReturnResponseDto;
    }
}