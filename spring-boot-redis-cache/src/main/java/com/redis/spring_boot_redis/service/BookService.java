package com.redis.spring_boot_redis.service;


import com.redis.spring_boot_redis.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    Book findBookById(long id);
    List<Book> findAllBooks();
    //Page<Book> findAllBooks();

    //Book findBookByTitleAndAuthor(String title, String author);
    Book saveBook(Book book);
    //Book saveBookWithoutCachePut(Book book);
    Book updateBook(Book book);
    //void deleteBook(Book book);

    void deleteBookById(Long id);
    //void deleteBookWithoutCacheEvict(Book book);
    long deleteAllBooks();
}
