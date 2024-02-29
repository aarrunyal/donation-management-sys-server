package com.donationmanagementsystem.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.donationmanagementsystem.payload.response.ApiResponse;

public class ResponseMessage {

    
    public static ResponseEntity<ApiResponse> internalServerError(String message){
        message = !(message == null) ?message:AppConstant.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ApiResponse> noContent(String message){
        message = !(message == null) ?message:AppConstant.NO_CONTETNT_FOUND;
        return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, message), HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<ApiResponse> created(String message){
        message = !(message == null) ?message:AppConstant.CREATED;
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, message), HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse> ok(String message){
        message = !(message == null) ?message:AppConstant.OK;
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, message), HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> notAcceptable(String message){
        message = !(message == null) ?message:AppConstant.NOT_ACCEPTABLE;
        return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, message), HttpStatus.NOT_ACCEPTABLE);
    }
}
