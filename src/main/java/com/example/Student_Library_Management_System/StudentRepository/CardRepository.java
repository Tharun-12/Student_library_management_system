package com.example.Student_Library_Management_System.StudentRepository;

import com.example.Student_Library_Management_System.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer>
{

}
