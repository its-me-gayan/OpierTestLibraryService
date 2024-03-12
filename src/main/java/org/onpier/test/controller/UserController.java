package org.onpier.test.controller;

import org.onpier.test.dto.AbstractResponseDto;
import org.onpier.test.dto.UserResponseDto;
import org.onpier.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/borrowed-book")
    public ResponseEntity<AbstractResponseDto<List<UserResponseDto>>> getAllUserWhoBorrowedBooksByDate(
            @RequestParam(value = "date",required = false)
            @DateTimeFormat(pattern = "MM/dd/yyyy") Date requestDate
    ) throws Exception {

        AbstractResponseDto<List<UserResponseDto>> abstractResponseDto = null;
        if(Objects.nonNull(requestDate)){
            abstractResponseDto = userService.getAllUserWhoBorrowedBooksByDate(requestDate);
        }else {
            abstractResponseDto= userService.getAllUserWhoBorrowedBooks();
        }
        return ResponseEntity.status(abstractResponseDto.getStatus()).body(abstractResponseDto);
    }

    @GetMapping("/non-terminated")
    public ResponseEntity<AbstractResponseDto<List<UserResponseDto>>> getAllNonTerminatedUserNotBorrow() throws Exception {
        AbstractResponseDto<List<UserResponseDto>> allNonTerminatedUserNotBorrowed = userService.getAllNonTerminatedUserNotBorrowed();
        return ResponseEntity.status(allNonTerminatedUserNotBorrowed.getStatus()).body(allNonTerminatedUserNotBorrowed);
    }

}
