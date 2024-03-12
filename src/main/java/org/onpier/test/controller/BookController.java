package org.onpier.test.controller;


import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.BookResponseDto;
import org.onpier.test.dto.UserResponseDto;
import org.onpier.test.service.BookService;
import org.onpier.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @GetMapping("/available")
    public ResponseEntity<AbstractResponseDto<List<BookResponseDto>>> getAllAvailableBooks()throws Exception{

        AbstractResponseDto<List<BookResponseDto>> allAvailableBooks = bookService.getAllAvailableBooks();
        return ResponseEntity.status(allAvailableBooks.getStatus()).body(allAvailableBooks);
    }

    @GetMapping("/borrowed")
    public ResponseEntity<AbstractResponseDto<List<BookResponseDto>>> getAllBorrowedBooks(
            @RequestParam("user") String user,
            @RequestParam("borrowedFrom") @DateTimeFormat(pattern = "MM/dd/yyyy") Date borrowedFrom,
            @RequestParam("borrowedTo") @DateTimeFormat(pattern = "MM/dd/yyyy") Date borrowedTo
    )throws Exception{

        AbstractResponseDto<List<BookResponseDto>> allAvailableBooks = bookService.getAllBorrowedBooks(user,borrowedFrom,borrowedTo);
        return ResponseEntity.status(allAvailableBooks.getStatus()).body(allAvailableBooks);
    }
}
