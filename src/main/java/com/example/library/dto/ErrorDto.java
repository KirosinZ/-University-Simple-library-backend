package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDto {

    private String message;

    private Date timestamp;

}
