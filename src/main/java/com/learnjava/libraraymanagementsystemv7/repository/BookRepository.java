package com.learnjava.libraraymanagementsystemv7.repository;

import com.learnjava.libraraymanagementsystemv7.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>
{
    
}
