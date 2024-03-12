package org.onpier.test.controller;

import org.apache.commons.lang3.compare.ComparableUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.BookResponseDto;
import org.onpier.test.dto.UserResponseDto;
import org.onpier.test.service.BookService;
import org.onpier.test.util.CsvDataConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private CsvDataConvertor csvDataConvertor;
    @MockBean
    private BookService bookService;
    @InjectMocks
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void api_Call_getAllAvailableBooks_Success() throws Exception {

        Mockito.when(bookService.getAllAvailableBooks())
                        .thenReturn(getAbstractResponse());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", CoreMatchers.is(Boolean.TRUE)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").value(Matchers.hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void api_Call_getAllBorrowedBooksByGivenUserAndDateRange_Success() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Mockito.when(bookService.getAllBorrowedBooks("Chish,Elijah",simpleDateFormat.parse("05/14/2008"),simpleDateFormat.parse("05/29/2008")))
                .thenReturn(getAbstractResponse());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books/borrowed?user=Chish,Elijah&borrowedFrom=05/14/2008&borrowedTo=05/29/2008"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", CoreMatchers.is(Boolean.TRUE)))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").value(Matchers.hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }


    private AbstractResponseDto<List<BookResponseDto>> getAbstractResponse(){
       return AbstractResponseDto.<List<BookResponseDto>>builder().data(
                       Collections.singletonList(BookResponseDto.builder()
                               .id(1)
                               .title("test book")
                               .author("test author")
                               .publisher("test publisher")
                               .genre("test genre").build())
               )
               .status(HttpStatus.OK)
               .isSuccess(Boolean.TRUE)
               .dateTime(LocalDateTime.now()).build();
   }
}