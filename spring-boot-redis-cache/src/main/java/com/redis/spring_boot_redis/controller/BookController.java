package com.redis.spring_boot_redis.controller;

import com.redis.spring_boot_redis.model.Book;
import com.redis.spring_boot_redis.service.impl.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private BookServiceImpl service;


    @GetMapping("/{id}")
    public Book findBookById(@PathVariable long id) {
        return service.findBookById(id);
    }


    @GetMapping("/all-books")
    public List<Book> findAllBooks() {
        return service.findAllBooks();
    }

    @PostMapping("save-book")
    public String saveBook(@RequestBody Book book) {
        service.saveBook(book);
        return "Book successfully saved";
    }

    @PutMapping("update-book")
    public Book updateBook(@RequestBody Book book) {
        return service.updateBook(book);
    }

    @DeleteMapping("delete-book/{id}")
    public void deleteBookById(@PathVariable Long id) {
        service.deleteBookById(id);
    }

}
