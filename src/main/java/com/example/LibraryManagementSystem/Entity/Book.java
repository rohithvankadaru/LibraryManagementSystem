package com.example.LibraryManagementSystem.Entity;

import com.example.LibraryManagementSystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int pages;

    private int price;

    @ManyToOne
    @JoinColumn
    private Author author;
}
