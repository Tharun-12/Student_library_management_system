package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController
{

    @Autowired
    TransactionService transactionService;


    @PostMapping("issuebook")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto)
    {

        try
        {
            return transactionService.issueBook(issueBookRequestDto);
        }
        catch(Exception e)
        {
            return e.getMessage();
        }

    }

    @GetMapping("/getTxnInfo")
    public String getTransactionEntry(@RequestParam("book") Integer bookId, @RequestParam("card") Integer cardId)
    {
        return transactionService.getTransactions(bookId, cardId);
    }

}
