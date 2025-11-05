package com.swp391.bookverse.exception;

import com.swp391.bookverse.dto.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author huangdat
 */

@ControllerAdvice
public class GlobalExeptionHandle {

//    /**
//     * Handles all runtime exceptions globally.
//     * But this is not recommended for production because it assigns a generic error code and message.
//     * @param e
//     * @return ResponseEntity with APIResponse containing error code and message
//     */
//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<APIResponse> handlingRuntimeException(RuntimeException e) {
//        APIResponse response = new APIResponse();
//
//        response.setCode(500);
//        response.setMessage(e.getMessage());
//
//        return ResponseEntity.badRequest().body(response);
//    }

    /**
     * Handles all runtime exceptions globally.
     * This will catch any unhandled exceptions in the application.
     * @param e
     * @return ResponseEntity with APIResponse containing error code and message
     */
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<APIResponse> handlingRuntimeException(RuntimeException e) {
        APIResponse response = new APIResponse();

        response.setCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode());
        response.setMessage(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles custom application exceptions.
     * @param e
     * @return ResponseEntity with APIResponse containing error code and message
     */
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<APIResponse> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        APIResponse response = new APIResponse();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles validation exceptions.
     * @param e
     * @return ResponseEntity with APIResponse containing error code and message
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse> handlingValidation(MethodArgumentNotValidException e) {

        String enumKey = e.getFieldError().getDefaultMessage();
        System.out.println(enumKey); // Debugging purpose

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Invalid enum key: " + enumKey);
            // If the enum key is not valid, we can either log it or handle it differently
        }

        APIResponse response = new APIResponse();

        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(response);
    }


    /**
     * Handles HTTP request method not supported exceptions.
     * @param e
     * @return ResponseEntity with APIResponse containing error code and message
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    ResponseEntity<APIResponse> handlingHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        APIResponse response = new APIResponse();

        response.setCode(ErrorCode.METHOD_NOT_ALLOWED.getCode());
        response.setMessage(ErrorCode.METHOD_NOT_ALLOWED.getMessage());

        return ResponseEntity.status(405).body(response);
    }
}
