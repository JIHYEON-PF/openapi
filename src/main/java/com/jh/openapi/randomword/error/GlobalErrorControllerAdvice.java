package com.jh.openapi.randomword.error;

import com.jh.openapi.randomword.error.dto.FailureResponse;
import com.jh.openapi.randomword.error.exception.BusinessException;
import com.jh.openapi.randomword.error.exception.ValidationException;
import com.jh.openapi.randomword.error.type.ForbiddenType;
import com.jh.openapi.randomword.error.type.InternalServerErrorType;
import com.jh.openapi.randomword.error.type.MethodNotAllowedType;
import com.jh.openapi.randomword.error.type.NotFoundType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalErrorControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<FailureResponse> handleBusinessException(HttpServletRequest request, BusinessException exception) {
        printLog(request, exception, exception.getStatus(), exception.getFailureResponse());

        return ResponseEntity
                .status(exception.getStatus())
                .body(exception.getFailureResponse());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<FailureResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        final MethodNotAllowedType errorType = MethodNotAllowedType.METHOD_NOT_ALLOWED;

        printLog(request, exception, errorType.getStatus(), errorType.toFailureResponse());

        return ResponseEntity
                .status(errorType.getStatus())
                .body(errorType.toFailureResponse());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<FailureResponse> handleNotHandlerFoundException(NoHandlerFoundException exception, HttpServletRequest request) {
        final NotFoundType errorType = NotFoundType.NOT_FOUND;

        printLog(request, exception, errorType.getStatus(), errorType.toFailureResponse());

        return ResponseEntity
                .status(errorType.getStatus())
                .body(errorType.toFailureResponse());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<FailureResponse> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException exception) {
        final ForbiddenType errorType = ForbiddenType.CANNOT_ACCESS_RESOURCE;

        printLog(request, exception, errorType.getStatus(), errorType.toFailureResponse());

        return ResponseEntity
                .status(errorType.getStatus())
                .body(errorType.toFailureResponse());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<FailureResponse> handleConstraintViolationException(HttpServletRequest request, BindException exception) {
        final ValidationException validationException = new ValidationException(exception.getFieldErrors());

        printLog(request, exception, validationException.getStatus(), validationException.getFailureResponse());

        return ResponseEntity
                .status(validationException.getStatus())
                .body(validationException.getFailureResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FailureResponse> handleException(HttpServletRequest request, Exception exception) {
        final InternalServerErrorType errorType = InternalServerErrorType.INTERNAL_SERVER_ERROR;

        exception.printStackTrace();
        printLog(request, exception, errorType.getStatus(), errorType.toFailureResponse());

        return ResponseEntity
                .status(errorType.getStatus())
                .body(errorType.toFailureResponse());
    }

    private void printLog(HttpServletRequest request, String exceptionClassName, HttpStatus status, String message) {
        log.error("<error response entity>, message-id: {}, exception: {}, status: {}, message: {}, uri: {}",
                request.getHeader("x-message-id"),
                exceptionClassName,
                status,
                message,
                request.getRequestURI());
    }

    private void printLog(HttpServletRequest request, Throwable exception, HttpStatus status, FailureResponse failureResponse) {
        log.error("<error response entity>, message-id: {}, exception: {}, status: {}, message: {}, uri: {}",
                request.getHeader("x-message-id"),
                exception == null ? null : exception.getClass().getName(),
                status,
                failureResponse.getMessage(),
                request.getRequestURI());
    }
}
