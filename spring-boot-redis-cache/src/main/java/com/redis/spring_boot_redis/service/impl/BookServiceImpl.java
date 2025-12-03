package com.redis.spring_boot_redis.service.impl;

import com.redis.spring_boot_redis.Exception.ResourceNotFoundException;
import com.redis.spring_boot_redis.model.Book;
import com.redis.spring_boot_redis.redisconfig.RedisConfig;
import com.redis.spring_boot_redis.repository.BookRepository;
import com.redis.spring_boot_redis.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private RedisConfig redisConfig;

    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisConfig redisConfig) {
        this.redisTemplate = redisConfig.redisTemplate();
    }

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book findBookById(long id) {
        var key = "book" + id;
        final ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            final Book post = (Book) operations.get(key);
            log.info("BookServiceImpl.findBookById() : cache post >> " + post.toString());
            return post;
        }
        final Optional<Book> book = repository.findById(id);
        if(book.isPresent()) {
            Object bookObj = book.get();
            operations.set(key, book.get(), 10, TimeUnit.SECONDS);
            log.info("BookServiceImpl.findBookById() : cache insert >> " + book.get().toString());
            return book.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Book saveBook(Book book) {
        log.info("Calling saveBook...");
        return repository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        final String key = "book_" + book.getId();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            log.info("BookServiceImpl.updateBook() : cache delete >> " + book.toString());
        }
        return repository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        final String key = "book_" + id;
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            log.info("BookServiceImpl.deletePost() : cache delete ID >> " + id);
        }
        final Optional<Book> book = repository.findById(id);
        if(book.isPresent()) {
            repository.delete(book.get());
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public long deleteAllBooks() {
        log.info("Calling deleteBook...");
        repository.deleteAll();
        long rowNumberAfterDelete = repository.count();

        return rowNumberAfterDelete;
    }

}
