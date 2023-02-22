package com.example.Student_Library_Management_System.DTOs;

import java.util.List;

public class AuthorResponseDto
{

    private int pages;

    private int age;
    private String Country;

    private List<BookResponseDto> booksWritten;
    private String name;
    private double rating;


    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public List<BookResponseDto> getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(List<BookResponseDto> booksWritten) {
        this.booksWritten = booksWritten;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
