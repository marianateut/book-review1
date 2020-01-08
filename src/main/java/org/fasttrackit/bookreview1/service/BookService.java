package org.fasttrackit.bookreview1.service;

import org.fasttrackit.bookreview1.domain.Book;
import org.fasttrackit.bookreview1.persisteance.BookRepository;
import org.fasttrackit.bookreview1.transfer.SaveBookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BookService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(SaveBookRequest request) {
        LOGGER.info("Creating book: {}", request);

        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setTitle(request.getTitle());
        book.setLanguage(request.getLanguage());
        book.setPages(request.getPages());
        book.setImagePath(request.getImagePath());
        book.setType(request.getType());
        book.setYearOfRelease(request.getYearOfRelease());
        book.setPrice(request.getPrice());


        return bookRepository.save(book);
    }



}
