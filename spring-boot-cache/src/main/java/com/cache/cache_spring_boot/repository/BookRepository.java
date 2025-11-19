package com.cache.cache_spring_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cache.cache_spring_boot.model.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitleAndAuthor(String title, String author);

    //void deleteAll();
    //long count();
}