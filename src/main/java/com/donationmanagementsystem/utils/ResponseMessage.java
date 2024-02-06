package com.donationmanagementsystem.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.payload.response.ApiResponse;

public class ResponseMessage {

    private final static String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private final static String NO_CONTETNT_FOUND = "No content found";
    private final static String CREATED = "Request processed successfully";
    private final static String OK = "Request processed successfully";
    private final static String NOT_ACCEPTABLE = "Request not acceptable";
    
    public static ResponseEntity<ApiResponse> internalServerError(String message){
        message = !(message == null) ?message:INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ApiResponse> noContent(String message){
        message = !(message == null) ?message:NO_CONTETNT_FOUND;
        return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, message), HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<ApiResponse> created(String message){
        message = !(message == null) ?message:CREATED;
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, message), HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse> ok(String message){
        message = !(message == null) ?message:OK;
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, message), HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> notAcceptable(String message){
        message = !(message == null) ?message:NOT_ACCEPTABLE;
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, message), HttpStatus.NOT_ACCEPTABLE);
    }
}
