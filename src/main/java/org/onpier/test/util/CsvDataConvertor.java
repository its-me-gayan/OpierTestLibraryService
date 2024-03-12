package org.onpier.test.util;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.onpier.test.OpierTestLibraryServiceRunner;
import org.onpier.test.entity.Book;
import org.onpier.test.entity.Borrowed;
import org.onpier.test.entity.User;
import org.onpier.test.repository.BookRepository;
import org.onpier.test.repository.BorrowedBookRepository;
import org.onpier.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CsvDataConvertor {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private UserRepository userRepository;
    public void insertData() throws Exception {
        URL booksPath = OpierTestLibraryServiceRunner.class.getResource("/file/books.csv");
        URL userPath = OpierTestLibraryServiceRunner.class.getResource("/file/user.csv");
        URL borrowedPath = OpierTestLibraryServiceRunner.class.getResource("/file/borrowed.csv");

        List<String[]> books = readAllLines(Path.of(booksPath.toURI()));
        List<String[]> users = readAllLines(Path.of(userPath.toURI()));
        List<String[]> borrowed = readAllLines(Path.of(borrowedPath.toURI()));
        books.remove(0);
        users.remove(0);
        borrowed.remove(0);

        books.forEach(book -> {

            if(
                    !StringUtils.isEmpty(book[0])&&
                            !StringUtils.isEmpty(book[1])&&
                            !StringUtils.isEmpty(book[2])&&
                            !StringUtils.isEmpty(book[3])
            ){
                Book build = Book.builder()
                        .title(book[0])
                        .author(book[1])
                        .genre(book[2])
                        .publisher(book[3])
                        .build();
                bookRepository.save(build);

            }

        });
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        users.forEach(user -> {
            if(
                    !StringUtils.isEmpty(user[0])&&
                            !StringUtils.isEmpty(user[1])&&
                            !StringUtils.isEmpty(user[2])&&
                            !StringUtils.isEmpty(user[4])
            ){
                try {
                    User build = User.builder()
                            .name(user[0])
                            .firstName(user[1])
                            .memberSince(simpleDateFormat.parse(user[2]))
                            .memberTill( (Objects.nonNull(user[3]) && !StringUtils.isEmpty(user[3])) ? simpleDateFormat.parse(user[3]) : null)
                            .gender(user[4])
                            .build();
                    userRepository.save(build);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        borrowed.forEach(borrow -> {
            if(
                    !StringUtils.isEmpty(borrow[0])&&
                            !StringUtils.isEmpty(borrow[1])&&
                            !StringUtils.isEmpty(borrow[2])&&
                            !StringUtils.isEmpty(borrow[3])
            ){
                try {
                    Borrowed build = Borrowed.builder().borrower(borrow[0])
                            .book(borrow[1])
                            .borrowedFrom(simpleDateFormat.parse(borrow[2]))
                            .borrowedTo(simpleDateFormat.parse(borrow[3]))
                            .build();
                    borrowedBookRepository.save(build);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}
