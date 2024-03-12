package org.onpier.test.repository;

import org.onpier.test.entity.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBookRepository extends JpaRepository<Borrowed , Integer> {

    boolean existsByborrower(String borrower);
    List<Borrowed> findByborrowerAndBorrowedFromGreaterThanEqualAndBorrowedToLessThanEqual(String borrower , Date borrowedFrom , Date borrowedTo);
    boolean existsBybook(String book);
    Optional<Borrowed> findBorrowedByborrowerAndBorrowedFrom(String borrower , Date borrowedFrom);
}
