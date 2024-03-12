package org.onpier.test.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title",nullable = false, updatable = false, length = 1024)
    private String title;

    @Column(name = "Author",length = 1024)
    private String author;

    @Column(name = "Genre",length = 1024)
    private String genre;

    @Column(name = "Publisher",length = 1024)
    private String publisher;


}
