package org.onpier.test.service;

import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.BookResponseDto;
import org.onpier.test.dto.UserResponseDto;

import java.util.Date;
import java.util.List;

public interface BookService {

    AbstractResponseDto<List<BookResponseDto>> getAllAvailableBooks()throws Exception;
    AbstractResponseDto<List<BookResponseDto>> getAllBorrowedBooks(String user , Date borrowedFrom , Date borrowedTo)throws Exception;
}
