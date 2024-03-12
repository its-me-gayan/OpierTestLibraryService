package org.onpier.test.service.impl;

import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.UserResponseDto;
import org.onpier.test.entity.User;
import org.onpier.test.repository.BorrowedBookRepository;
import org.onpier.test.repository.UserRepository;
import org.onpier.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Override
    public AbstractResponseDto<List<UserResponseDto>> getAllUserWhoBorrowedBooks() throws Exception {
        List<User> allUsers = userRepository.findAll();

        List<UserResponseDto> list = allUsers.stream().filter(user -> {
            String fullName = user.getName() + "," + user.getFirstName();
            return borrowedBookRepository.existsByborrower(fullName);
        }).map(user ->
             UserResponseDto.builder().id(user.getId())
                    .name(user.getName())
                    .firstName(user.getFirstName())
                    .gender(user.getGender())
                    .memberSince(user.getMemberSince())
                    .memberTill(user.getMemberTill()).build()
        ).toList();

        return AbstractResponseDto.<List<UserResponseDto>>builder().data(list)
                .status(HttpStatus.OK)
                .isSuccess(Boolean.TRUE)
                .dateTime(LocalDateTime.now()).build();
    }

    @Override
    public AbstractResponseDto<List<UserResponseDto>> getAllNonTerminatedUserNotBorrowed() throws Exception {
        List<User> allBymemberTillIsNotNull =
                userRepository.findAllBymemberTillIsNotNull();

        List<UserResponseDto> list = allBymemberTillIsNotNull.stream().filter(user ->
        {
            String fullName = user.getName() + "," + user.getFirstName();
            return !borrowedBookRepository.existsByborrower(fullName);
        }).map(user ->
                UserResponseDto.builder().id(user.getId())
                        .name(user.getName())
                        .firstName(user.getFirstName())
                        .gender(user.getGender())
                        .memberSince(user.getMemberSince())
                        .memberTill(user.getMemberTill()).build()
        ).toList();
        return AbstractResponseDto.<List<UserResponseDto>>builder().data(list)
                .status(HttpStatus.OK)
                .isSuccess(Boolean.TRUE)
                .dateTime(LocalDateTime.now()).build();
    }

    @Override
    public AbstractResponseDto<List<UserResponseDto>> getAllUserWhoBorrowedBooksByDate(Date requestDate) throws Exception {
        List<User> allUsers = userRepository.findAll();

        List<UserResponseDto> list = allUsers.stream().filter(user -> {
            String fullName = user.getName() + "," + user.getFirstName();
            return borrowedBookRepository.findBorrowedByborrowerAndBorrowedFrom(fullName,requestDate).isPresent();
        }).map(user ->
                UserResponseDto.builder().id(user.getId())
                        .name(user.getName())
                        .firstName(user.getFirstName())
                        .gender(user.getGender())
                        .memberSince(user.getMemberSince())
                        .memberTill(user.getMemberTill()).build()
        ).toList();

        return AbstractResponseDto.<List<UserResponseDto>>builder().data(list)
                .status(HttpStatus.OK)
                .isSuccess(Boolean.TRUE)
                .dateTime(LocalDateTime.now()).build();
    }
}
