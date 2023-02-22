package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.StudentRepository.BookRepository;
import com.example.Student_Library_Management_System.StudentRepository.CardRepository;
import com.example.Student_Library_Management_System.StudentRepository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transaction;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService
{
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;



    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception
    {
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        // Get the Book Entity and card Entity ?? Why do need this.
        // We are doing this set transaction attributes.

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        // Final goal is to make transaction Entity,set attributes.
        // and save it

        Transactions transaction = new Transactions();

        //Setting attributes
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperations(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        if(book == null || book.isIssued()== true)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not Available");
        }

        if(card == null || card.getCardStatus() != CardStatus.ACTIVATED)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);

            throw new Exception("Card is not Valid");

        }


        // Do validations

        transaction.setTransactionStatus(TransactionStatus.SUCESS);




        // foriegn key
        book.setIssued(true);

        List<Transactions>  listOfTransactionForBook = book.getListOfTransactions();
        listOfTransactionForBook.add(transaction);
        book.setListOfTransactions(listOfTransactionForBook);

        List<Book> issuedBookForCard = card.getBooksIssued();
        issuedBookForCard.add(book);
        card.setBooksIssued(issuedBookForCard);

        List<Transactions> transactionsListForCard = card.getTransactions();
        transactionsListForCard.add(transaction);
        card.setTransactions(transactionsListForCard);

        cardRepository.save(card);

        return "Book issued successfully";
    }

    public String getTransactions(int bookId, int cardId)
    {
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId, cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }
}
