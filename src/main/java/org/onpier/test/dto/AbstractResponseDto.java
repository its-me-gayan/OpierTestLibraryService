package org.onpier.test.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbstractResponseDto<T> {
    private LocalDateTime dateTime;
    private HttpStatus status;
    private boolean isSuccess;
    private T data;
}
