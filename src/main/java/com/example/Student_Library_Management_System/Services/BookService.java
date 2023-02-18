package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.StudentRepository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book)
    {
        // I want to get Entity
        int authorId = book.getAuthor().getId();

        // Now I will fetching the authorEntity

        Author author= authorRepository.findById(authorId).get();

        //setting the foriegn key attr child class

        book.setAuthor(author);

        // We need to update the listOfBooks Written in parent class

        List<Book> currentBooksWritten = author.getBooksWritten();

        currentBooksWritten.add(book);

        author.setBooksWritten(currentBooksWritten);

        // Now the book is to saved, but also author also saved.

        // why do we need to again save(updating) ... we need to resave/update.

        authorRepository.save(author); // Data was modified.

        //.save function works both save and update function.

        return "Book added Sucessfully";
    }
}
