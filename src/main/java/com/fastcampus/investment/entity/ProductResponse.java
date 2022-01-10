package com.fastcampus.investment.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse<T> {
    private T data;
    private HttpStatus status;
}
