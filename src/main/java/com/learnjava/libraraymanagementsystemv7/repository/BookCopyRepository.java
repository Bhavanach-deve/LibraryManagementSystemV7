package com.learnjava.libraraymanagementsystemv7.repository;

import com.learnjava.libraraymanagementsystemv7.entity.BookCopy;
import com.learnjava.libraraymanagementsystemv7.entity.BookCopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCopyRepository
        extends JpaRepository<BookCopy, Integer> {

    List<BookCopy> findByBookId(int bookId);

    Optional<BookCopy> findFirstByBookIdAndStatus(
            int bookId,
            BookCopyStatus status
    );
}       