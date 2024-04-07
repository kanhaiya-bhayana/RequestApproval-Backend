package com.kanhaiya.requestapproval.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class ResponseDto {


    private String statusCode;


    private String statusMessage;
}
