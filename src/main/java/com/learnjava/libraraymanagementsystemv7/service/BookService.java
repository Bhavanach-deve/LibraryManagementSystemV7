package com.learnjava.libraraymanagementsystemv7.service;

import com.learnjava.libraraymanagementsystemv7.dto.BookRequest;
import com.learnjava.libraraymanagementsystemv7.dto.BookResponse;
import com.learnjava.libraraymanagementsystemv7.entity.Book;
import com.learnjava.libraraymanagementsystemv7.exception.BookNotFoundException;
import com.learnjava.libraraymanagementsystemv7.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class BookService
{
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public BookResponse addBook(BookRequest bookRequest) {

        Book book = new Book();

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setAvailable(bookRequest.isAvailable());

        Book savedBook = bookRepository.save(book);
        return toBookResponse(savedBook);
    }
    public Page<BookResponse> getAllBooks(Pageable pageable) {

        Page<Book> bookPage = bookRepository.findAll(pageable);

        return bookPage.map(this::toBookResponse);
    }
    public BookResponse getBookById(int id) {

        Book book = findBookById(id);

        return toBookResponse(book);
    }
    public void deleteBookById(int id) {

        Book book=findBookById(id);

        bookRepository.delete(book);
    }
    public BookResponse updateBook(int id, BookRequest bookRequest) {

        Book existingBook = findBookById(id);
        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());
        existingBook.setAvailable(bookRequest.isAvailable());

        Book savedBook= bookRepository.save(existingBook);

        return toBookResponse(savedBook);
    }
    private BookResponse toBookResponse(Book book) {

        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setAvailable(book.isAvailable());

        return response;
    }
    private Book findBookById(int id) {

        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }

        throw new BookNotFoundException(
                "Book with id " + id + " not found"
        );
    }
}

