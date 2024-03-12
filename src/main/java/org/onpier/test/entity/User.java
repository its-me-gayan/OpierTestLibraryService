package org.onpier.test.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;


@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "Name",nullable = false, updatable = false, length = 1024)
    private String name;

    @Column(name = "FirstName",length = 1024)
    private String firstName;

    @Column(name = "MemberSince")
    private Date memberSince;

    @Column(name = "MemberTill",nullable = true)
    private Date memberTill;

    @Column(name = "Gender",length = 1024)
    private String gender;


}
