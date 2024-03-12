package org.onpier.test.repository;

import org.onpier.test.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , Integer> {

    Optional<Book> findBytitle(String title);
}
