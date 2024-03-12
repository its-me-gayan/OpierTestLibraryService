package org.onpier.test.service.impl;

import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.BookResponseDto;
import org.onpier.test.dto.UserResponseDto;
import org.onpier.test.entity.Book;
import org.onpier.test.entity.Borrowed;
import org.onpier.test.repository.BookRepository;
import org.onpier.test.repository.BorrowedBookRepository;
import org.onpier.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Override
    public AbstractResponseDto< List<BookResponseDto> > getAllAvailableBooks() throws Exception {

        List<Book> all = bookRepository.findAll();
        List<BookResponseDto> list = all.stream().filter(book -> !borrowedBookRepository.existsBybook(book.getTitle()))
                .map(book ->
                        BookResponseDto.builder()
                                .id(book.getId())
                                .title(book.getTitle())
                                .author(book.getAuthor())
                                .publisher(book.getPublisher())
                                .genre(book.getGenre()).build()
                ).toList();

        return AbstractResponseDto.<List<BookResponseDto>>builder().data(list)
                .status(HttpStatus.OK)
                .isSuccess(Boolean.TRUE)
                .dateTime(LocalDateTime.now()).build();
    }

    @Override
    public AbstractResponseDto<List<BookResponseDto>> getAllBorrowedBooks(String user, Date borrowedFrom, Date borrowedTo) throws Exception {

        List<Borrowed> list = borrowedBookRepository.findByborrowerAndBorrowedFromGreaterThanEqualAndBorrowedToLessThanEqual(user, borrowedFrom, borrowedTo);

        List<BookResponseDto> all = list.stream().map(borrowed -> {
            Optional<Book> bytitle = bookRepository.findBytitle(borrowed.getBook());
            if (bytitle.isPresent()) {
                Book book = bytitle.get();
                return BookResponseDto.builder()
                        .id(book.getId())
                        .genre(book.getGenre())
                        .publisher(book.getPublisher())
                        .title(book.getTitle())
                        .author(book.getAuthor()).build();
            }
            return null;
        }).toList();

        return AbstractResponseDto.<List<BookResponseDto>>builder().data(all)
                .status(HttpStatus.OK)
                .isSuccess(Boolean.TRUE)
                .dateTime(LocalDateTime.now()).build();

    }
}
