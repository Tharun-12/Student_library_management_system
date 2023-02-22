package com.example.Student_Library_Management_System.Models;


import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card
{


    // Plan is to save a card in Db
    // Before Saving I have to set its attributes.


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp //Auto timestamp the time when an entry is created
   private Date createdOn;

    @UpdateTimestamp // Sets a time when an entry is updated.
    private Date updateOn;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;


    @OneToOne
    @JoinColumn
    private Student studentVariableName; // This variable is used in parent class.
    // While doing Bidirectional Mapping.


    // Card is a parent wrt to book
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Book> booksIssued = new ArrayList<>();

    // Connection card class to transaction
    //Bidirectional Mapping

    @OneToMany(mappedBy = "card",cascade=CascadeType.ALL)
    private List<Transactions> transactions = new ArrayList<>();

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Student getStudentVariableName() {
        return studentVariableName;
    }

    public void setStudentVariableName(Student studentVariableName) {
        this.studentVariableName = studentVariableName;
    }

    public Card()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }



}
