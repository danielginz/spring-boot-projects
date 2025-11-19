package com.cache.cache_spring_boot.service;

import com.cache.cache_spring_boot.model.Book;
import com.cache.cache_spring_boot.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest

//reload context after each test method. We want every new test will run on a new clean DB and cache
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceTest.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BookService service;

    @MockitoSpyBean
    @Autowired
    private BookRepository repository;

    @Test
    void testFindBookById() {
        final long bookId = 1L;

        // первое обращение к сервису, получение данных из БД и кэширование
        Book book = service.findBookById(bookId);
        assertNotNull(book, "Book is not found");
        LOG.info("Book: " + book.getTitle());

        // второе обращение к сервису, получение данных из кэша
        Book cachedBook = service.findBookById(bookId);
        assertNotNull(cachedBook, "Book is not found");
        LOG.info("Book: " + book.getTitle());

        // данные из БД извлекаются только 1 раз при первом обращении
        verify(repository, times(1)).findById(bookId);

        //Evaluate this expression to see how is cache looking
        //applicationContext.getBean("cacheManager");
    }

    @Test
    void testFindBookByIdNullResult() {
        final long bookId = 10L;

        // первое обращение к сервису, в ответ возвращается null
        Book book1 = service.findBookById(bookId);
        assertNull(book1, "Book is found");

        // второе обращение к сервису, в ответ возвращается null
        // т.к. у нас условие не кэшировать значение которое равно null, то опять идём в DB
        //@Cacheable(value = "book", unless = "#result == null")
        Book book2 = service.findBookById(bookId);
        assertNull(book2, "Book is found");

        // так как значение null не кэшируется, то к БД будет 2 обращения
        verify(repository, times(2)).findById(bookId);

        applicationContext.getBean("cacheManager");
    }

    @Test
    void testFindBookByTitleAndAuthor() {
        // первое обращение к сервису, получение данных из БД и кэширование
        Book book = service.findBookByTitleAndAuthor("And Quiet Flows the Don", "M. A. Sholokhov");
        assertNotNull(book, "Book is not found");
        LOG.info("Book: " + book.getTitle());

        // второе обращение к сервису, получение данных из кэша
        Book cachedBook = service.findBookByTitleAndAuthor("And Quiet Flows the Don", "M. A. Sholokhov");
        assertNotNull(cachedBook, "Book is not found");
        LOG.info("Book: " + book.getTitle());

        // данные из БД извлекаются только 1 раз при первом обращении
        verify(repository, times(1)).findBookByTitleAndAuthor("And Quiet Flows the Don", "M. A. Sholokhov");

        applicationContext.getBean("cacheManager");
    }

    @Test
    void testSaveBookAndPutCache() {
        final long bookId = 4L;

        Book book = new Book(bookId, "The Captain's Daughter", "A. S. Pushkin");

        // сохранение и кэширование
        Book savedBook = service.saveBook(book);

        // получение данных из кэша
        Book foundedBook = service.findBookById(savedBook.getId());
        assertNotNull(foundedBook, "Book is not found");
        LOG.info("Book: " + foundedBook.getTitle());

        // данные закэшированы при сохранении и при запросе из БД не извлекаются
        verify(repository, never()).findById(bookId);
    }

    //save book in DB without saving into cache
    @Test
    void testSaveBookWithoutPutCache() {
        final long bookId = 5L;

        Book book = new Book(bookId, "War and Peace", "L. N. Tolstoy");

        // сохранение без кэширования
        Book savedBook = service.saveBookWithoutCachePut(book);

        // первое обращение к сервису, получение данных из БД и кэширование
        Book foundedBook = service.findBookById(savedBook.getId());
        assertNotNull(foundedBook, "Book is not found");
        LOG.info("Book: " + foundedBook.getTitle());

        // второе обращение к сервису, получение данных из кэша
        foundedBook = service.findBookById(savedBook.getId());
        assertNotNull(foundedBook, "Book is not found");
        LOG.info("Book: " + foundedBook.getTitle());

        // данные из БД извлекаются только 1 раз при первом обращении
        verify(repository, times(1)).findById(bookId);
    }


    @Test
    void testDeleteBookAndCacheEvict() {
        final long bookId = 2L;

        // получение данных из БД и кэширование
        Book foundedBook = service.findBookById(bookId);

        // удаление данных из БД и кэша
        service.deleteBook(foundedBook);

        //it has return null, because the book will be deleted from the DB anc cache
        foundedBook = service.findBookById(bookId);
        assertNull(foundedBook, "Book is found");
    }




    //don't delete without @CacheEvict beacause then the data will be inconsistent
    //this test checks incorrect work of the application
    @Test
    void testDeleteBookWithoutCacheEvict() {
        final long bookId = 3L;

        // первый вызов findBookById, получение данных из БД и кэширование
        Book foundedBook = service.findBookById(bookId);

        // удаление данных из БД
        service.deleteBookWithoutCacheEvict(foundedBook);

        // второй вызов findBookById, получение закэшированных данных; it was deleted from DB but not from cache
        foundedBook = service.findBookById(bookId);
        assertNotNull(foundedBook, "Book is not found");
        LOG.info("Book: " + foundedBook.getTitle());

        // данные из БД извлекаются только 1 раз при первом обращении
        verify(repository, times(1)).findById(bookId);
    }

    @Test
    void testDeleteAllBooksAndCacheEvict() {
        long numOfRows = service.deleteAllBooks();
        assertEquals(0, numOfRows);
    }

    /**
     * Пример того как можно получить экземпляр бина cacheManager для анализа
     */
    private void cacheManagerBean() {
        applicationContext.getBean("cacheManager");
    }

}
