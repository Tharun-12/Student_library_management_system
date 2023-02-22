package com.example.Student_Library_Management_System.Models;


import com.example.Student_Library_Management_System.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int pages;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    // Book is child wrt another
    // Setting here foreign key : 3 Standard

    @ManyToOne
    @JoinColumn // add extra attribute of authorId (parent table PK) for foreign key of child class
    private Author author;


    // Book is also child wrt
    @ManyToOne
    @JoinColumn
    private Card card;



    private boolean issued;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)

    private List<Transactions> ListOfTransactions = new ArrayList<>();

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isIssued() {
        return issued;
    }

    public List<Transactions> getListOfTransactions() {
        return ListOfTransactions;
    }

    public void setListOfTransactions(List<Transactions> listOfTransactions) {
        ListOfTransactions = listOfTransactions;
    }

    public Book()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public void setIssued(boolean issued) {
        this.issued = issued;
    }
}
