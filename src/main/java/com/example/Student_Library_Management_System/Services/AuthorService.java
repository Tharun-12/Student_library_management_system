package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.StudentRepository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    AuthorRepository authorRepository;
    public String createAuthor(AuthorEntryDto authorEntryDto)
    {
        // import step in params : object is of type dtos
        // of type DTO but repository interacts only with entites

        Author author = new Author();
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

      authorRepository.save(author);

      return "Author Added Sucessfully";
    }

    public AuthorResponseDto getAuthorId(Integer authorId)
    {
        Author author = authorRepository.findById(authorId).get();

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        List<Book> bookList = author.getBooksWritten();

        List<BookResponseDto> booksWrittenDto = new ArrayList<>();

        for(Book book : bookList)
        {
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setPages(book.getPages());
            bookResponseDto.setName(book.getName());

            booksWrittenDto.add(bookResponseDto);

        }

        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());

        return authorResponseDto;
    }
}
