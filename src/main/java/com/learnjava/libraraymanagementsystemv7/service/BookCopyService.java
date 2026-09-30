package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.BookCopyRequest;
import com.learnjava.libraraymanagementsystemv7.dto.BookCopyResponse;
import com.learnjava.libraraymanagementsystemv7.entity.Book;
import com.learnjava.libraraymanagementsystemv7.entity.BookCopy;
import com.learnjava.libraraymanagementsystemv7.entity.BookCopyStatus;
import com.learnjava.libraraymanagementsystemv7.exception.BookNotFoundException;
import com.learnjava.libraraymanagementsystemv7.repository.BookCopyRepository;
import com.learnjava.libraraymanagementsystemv7.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyService(
            BookCopyRepository bookCopyRepository,
            BookRepository bookRepository) {

        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
    }

    public BookCopyResponse addBookCopy(BookCopyRequest request) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book with id " + request.getBookId() + " not found"
                        ));

        BookCopy bookCopy = new BookCopy();

        bookCopy.setBook(book);
        bookCopy.setCopyNumber(request.getCopyNumber());
        bookCopy.setStatus(BookCopyStatus.AVAILABLE);

        BookCopy savedBookCopy =
                bookCopyRepository.save(bookCopy);

        return toBookCopyResponse(savedBookCopy);
    }

    private BookCopyResponse toBookCopyResponse(BookCopy bookCopy) {

        BookCopyResponse response = new BookCopyResponse();

        response.setId(bookCopy.getId());
        response.setBookId(bookCopy.getBook().getId());
        response.setCopyNumber(bookCopy.getCopyNumber());
        response.setStatus(bookCopy.getStatus().name());

        return response;
    }
}