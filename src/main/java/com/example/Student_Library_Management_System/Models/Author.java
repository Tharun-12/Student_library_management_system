package com.example.Student_Library_Management_System.Models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private int age;

  private String country;

  private double rating;

  // This annotations I am writing in parent class
  // Part of bidirectional Mapping

  @OneToMany(mappedBy ="",cascade =CascadeType.ALL)
  List<Book> booksWritten  = new ArrayList<>();

  public Author()
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public List<Book> getBooksWritten() {
    return booksWritten;
  }

  public void setBooksWritten(List<Book> booksWritten) {
    this.booksWritten = booksWritten;
  }
}
