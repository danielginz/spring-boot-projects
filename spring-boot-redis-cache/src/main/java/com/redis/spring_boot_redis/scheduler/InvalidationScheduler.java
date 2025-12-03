package com.redis.spring_boot_redis.scheduler;


import com.redis.spring_boot_redis.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InvalidationScheduler {

    @Autowired
    private BookServiceImpl service;

    @Scheduled(cron = "* 0 0 * * ?")
    public void invalidateCache() {
        service.deleteAllBooks();
    }
}
