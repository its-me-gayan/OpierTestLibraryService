package org.onpier.test.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.BookResponseDto;
import org.onpier.test.entity.Book;
import org.onpier.test.repository.BookRepository;
import org.onpier.test.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BorrowedBookRepository borrowedBookRepository;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    void service_getAllAvailableBooksTest_Success() throws Exception {
        Book book = Book.builder()
                .id(1)
                .title("test book")
                .author("test author")
                .publisher("test publisher")
                .genre("test genre").build();

        Mockito.when(bookRepository.findAll())
                .thenReturn(Collections.singletonList(book));
        Mockito.when(borrowedBookRepository.existsBybook(book.getTitle())).thenReturn(Boolean.FALSE);

        AbstractResponseDto<List<BookResponseDto>> allAvailableBooks = bookServiceImpl.getAllAvailableBooks();
        assertNotNull(allAvailableBooks);
        assertTrue(allAvailableBooks.isSuccess());
        assertEquals(HttpStatus.OK , allAvailableBooks.getStatus());
        assertNotNull(allAvailableBooks.getData());
        assertTrue(!allAvailableBooks.getData().isEmpty());

    }

    @Test
    void getAllBorrowedBooks() {
    }
}