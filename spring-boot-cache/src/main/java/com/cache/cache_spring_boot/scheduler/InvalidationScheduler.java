package com.cache.cache_spring_boot.scheduler;

import com.cache.cache_spring_boot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InvalidationScheduler {

    @Autowired
    private BookService service;

    @Scheduled(cron = "* 0 0 * * ?")
    public void invalidateCache() {
        service.deleteAllBooks();
    }
}
