package com.learnjava.libraraymanagementsystemv7.exception;

import com.learnjava.libraraymanagementsystemv7.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookNotFound(BookNotFoundException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {

            errors.put(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation failed");
        response.setErrors(errors);

        return response;
    }
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleMemberNotFound(
            MemberNotFoundException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(LibrarianNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleLibrarianNotFound(
            LibrarianNotFoundException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(AppUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAppUserNotFound(
            AppUserNotFoundException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(InvalidInvitationTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidInvitationToken(
            InvalidInvitationTokenException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(InvitationExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvitationExpired(
            InvitationExpiredException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(InvitationAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvitationAlreadyUsed(
            InvitationAlreadyUsedException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(BookCopyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookCopyNotFound(
            BookCopyNotFoundException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(BookCopyNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBookCopyNotAvailable(
            BookCopyNotAvailableException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(ActiveLoanAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleActiveLoanAlreadyExists(
            ActiveLoanAlreadyExistsException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleLoanNotFound(
            LoanNotFoundException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());

        return response;
    }

    @ExceptionHandler(LoanAlreadyReturnedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleLoanAlreadyReturned(
            LoanAlreadyReturnedException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return response;
    }
    @ExceptionHandler(MaximumActiveLoansExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMaximumActiveLoansExceeded(
            MaximumActiveLoansExceededException exception) {

        ErrorResponse response = new ErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());

        return response;
    }

}

