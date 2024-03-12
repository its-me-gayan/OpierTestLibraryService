package org.onpier.test.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.UserResponseDto;
import org.onpier.test.entity.User;
import org.onpier.test.repository.BorrowedBookRepository;
import org.onpier.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BorrowedBookRepository borrowedBookRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void service_getAllUserWhoBorrowedBooks_Success() throws Exception {
        User user = User.builder()
                .id(1)
                .name("test user")
                .firstName("fname")
                .gender("m")
                .memberSince(Date.from(Instant.now()))
                .memberTill(Date.from(Instant.now())).build();

        Mockito.when(userRepository.findAll())
                .thenReturn(Collections.singletonList(user));
        String fullName = user.getName() + "," + user.getFirstName();

        Mockito.when(borrowedBookRepository.existsByborrower(fullName))
                .thenReturn(Boolean.TRUE);
        AbstractResponseDto<List<UserResponseDto>> allUserWhoBorrowedBooks = userService.getAllUserWhoBorrowedBooks();

        assertNotNull(allUserWhoBorrowedBooks);
        assertTrue(allUserWhoBorrowedBooks.isSuccess());
        assertEquals(HttpStatus.OK , allUserWhoBorrowedBooks.getStatus());
        assertNotNull(allUserWhoBorrowedBooks.getData());
        assertTrue(!allUserWhoBorrowedBooks.getData().isEmpty());
    }

    @Test
    void getAllNonTerminatedUserNotBorrowed() {
    }

    @Test
    void getAllUserWhoBorrowedBooksByDate() {
    }
}