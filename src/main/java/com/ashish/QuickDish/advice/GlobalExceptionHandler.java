package com.ashish.QuickDish.advice;


import com.ashish.QuickDish.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResouceNotFound(ResourceNotFoundException exception){
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setMessage(exception.getMessage());


        return buildErrorResponseEntity(apiError);
    }

//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }
//
//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.UNAUTHORIZED)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.FORBIDDEN)
//                .message(ex.getMessage())
//                .build();
//        return buildErrorResponseEntity(apiError);
//    }

        @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){

            ApiError apiError = new ApiError();
            apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiError.setMessage(exception.getMessage());


            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON) // <-- Yeh line jaruri hai
                    .body(new ApiResponse<>(apiError));
        }
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }
}
