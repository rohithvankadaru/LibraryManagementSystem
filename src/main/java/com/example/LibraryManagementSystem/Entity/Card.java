package com.example.LibraryManagementSystem.Entity;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date issueDate = new Date();

    @UpdateTimestamp
    private Date updatedOn;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    private Date validTill;

    @OneToOne
    @JoinColumn
    private Student student;

    public Card(CardStatus cardStatus, Student student) {
        this.cardStatus = cardStatus;
        this.student = student;
    }
}
