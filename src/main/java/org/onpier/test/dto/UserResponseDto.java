package org.onpier.test.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDto {

    private Integer id;

    private String name;

    private String firstName;

    private Date memberSince;

    private Date memberTill;

    private String gender;
}
