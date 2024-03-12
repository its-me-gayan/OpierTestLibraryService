package org.onpier.test.service;

import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.UserResponseDto;

import java.util.Date;
import java.util.List;

public interface UserService {
    AbstractResponseDto<List<UserResponseDto>> getAllUserWhoBorrowedBooks() throws Exception;
    AbstractResponseDto<List<UserResponseDto>> getAllNonTerminatedUserNotBorrowed() throws Exception;
    AbstractResponseDto<List<UserResponseDto>> getAllUserWhoBorrowedBooksByDate(Date requestDate) throws Exception;
}
