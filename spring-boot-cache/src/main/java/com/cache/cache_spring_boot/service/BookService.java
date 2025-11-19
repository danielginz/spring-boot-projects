package com.cache.cache_spring_boot.service;

import com.cache.cache_spring_boot.model.Book;
import com.cache.cache_spring_boot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository repository;

    @Cacheable(value = "book", unless = "#result == null")
    public Book findBookById(long id) {
        LOG.info("Calling getBookById...");
        Optional<Book> bookOptional = repository.findById(id);
        return bookOptional.orElse(null);
    }

    @Cacheable(value = "book", key = "#title", unless = "#result == null")
    //key = "#title" - title will be key for a value that will be put into cache
    public Book findBookByTitleAndAuthor(String title, String author) {
        LOG.info("Calling getBookById...");
        Optional<Book> bookOptional = repository.findBookByTitleAndAuthor(title, author);
        return bookOptional.orElse(null);
    }

    //@CachePut forcedly put value into cache(after it's saved in DB)
    //value = "book" - name of cache, where the book will be saved(after it's saved in DB)
    //key = "book".id - id of the book will be a key
    @CachePut(value = "book", key = "#book.id")
    public Book saveBook(Book book) {
        LOG.info("Calling saveBook...");
        return repository.save(book);
    }

    public Book saveBookWithoutCachePut(Book book) {
        LOG.info("Calling saveBookWithoutCachePut...");
        return repository.save(book);
    }

    //@CacheEvict - emoves a book from DB and after that from the "book" cache by the key = "#book.id"
    @CacheEvict(value = "book", key = "#book.id")
    public void deleteBook(Book book) {
        LOG.info("Calling deleteBook...");
        repository.delete(book);
    }

    public void deleteBookWithoutCacheEvict(Book book) {
        LOG.info("Calling deleteBook...");
        repository.delete(book);
    }

    @CacheEvict(value = "book", allEntries = true)
    public long deleteAllBooks() {

        //long rowNumberBefore = repository.count();
        LOG.info("Calling deleteBook...");
        repository.deleteAll();
        long rowNumberAfterDelete = repository.count();

        return rowNumberAfterDelete;
    }

}
