package org.onpier.test.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "borrowed")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Borrowed {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "Borrower",nullable = false, updatable = false, length = 1024)
    private String borrower;

    @Column(name = "Book",length = 1024)
    private String book;

    @Column(name = "BorrowedFrom")
    private Date borrowedFrom;

    @Column(name = "BorrowedTo")
    private Date borrowedTo;


}
