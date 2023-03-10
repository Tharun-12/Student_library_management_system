package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{


    @Autowired
    StudentRepository studentRepository;
    public String createStudent(Student student)
    {
      // Student from the postman already the basic attributes set
      // Card Should be autogenerated when createStudent function is called.

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudentVariableName(student);

        // Lets go to student

        student.setCard(card);

        studentRepository.save(student);

        return "Student and Card added Successfully";
    }

    public String getUserByEmail(String email)
    {
      Student student = studentRepository.findByEmail(email);

      return student.getName();
    }


    public String updateMobNo(StudentUpdateMobRequestDto StudentReq)
    {
        //// First try to fetch original Data
        Student originalStudent = studentRepository.findById(StudentReq.getId()).get();

        // We will keep the other properties it is : and only change required parameters.

        originalStudent.setMobNo(StudentReq.getMobNo());

        studentRepository.save(originalStudent);


        return "Student has been updated Successfully";
    }
}
