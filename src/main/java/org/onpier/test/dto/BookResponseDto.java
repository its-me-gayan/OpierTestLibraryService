package org.onpier.test.dto;


import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Integer id;

    private String title;

    private String author;

    private String genre;

    private String publisher;
}
