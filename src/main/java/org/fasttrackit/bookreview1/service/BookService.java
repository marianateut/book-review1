package org.fasttrackit.bookreview1.service;

import org.fasttrackit.bookreview1.domain.Book;
import org.fasttrackit.bookreview1.exception.ResourceNotFoundException;
import org.fasttrackit.bookreview1.persisteance.BookRepository;
import org.fasttrackit.bookreview1.transfer.BookResponse;
import org.fasttrackit.bookreview1.transfer.GetBookRequest;
import org.fasttrackit.bookreview1.transfer.SaveBookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public Book getBook(long id) {
        LOGGER.info("Retrieving Book ", id);
        return bookRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Book " + id + " not found"));
    }

    @Transactional
    public Page<BookResponse> getBooks(GetBookRequest request, Pageable pageable) {
        LOGGER.info("retrieving books: {}", request);
        Page<Book> books;
        if (request != null && request.getPartialTitle() != null && request.getPartialAuthor() != null) {
            books = bookRepository.findByTitleAndAuthor(request.getPartialTitle(),
                    request.getPartialAuthor(), pageable);


        } else
            if (request != null && request.getPartialTitle() != null) {
                books = bookRepository.findByTitleOrAuthor(request.getPartialTitle(), pageable);
            } else
                {
                books = bookRepository.findAll(pageable);
                }
            List<BookResponse> bookResponses = new ArrayList<>();
            for (Book book : books.getContent()) {
                BookResponse bookResponse = new BookResponse();
                bookResponse.setId(book.getId());
                bookResponse.setTitle(book.getTitle());
                bookResponse.setPrice(book.getPrice());
                bookResponse.setDescription(book.getDescription());
                bookResponse.setAuthor(book.getAuthor());
                bookResponse.setLanguage(book.getLanguage());

                bookResponses.add(bookResponse);
            }
            return new PageImpl<>(bookResponses, pageable, books.getTotalElements());
        }


    public Book updateBook(long id, SaveBookRequest request) {
        LOGGER.info("updating Book {}: {}", id, request);

        Book book = getBook(id);
        BeanUtils.copyProperties(request, book);
        return bookRepository.save(book);
    }

    public void deleteBook(long id){
        LOGGER.info("deleting book {}", id);
        bookRepository.deleteById(id);
    }

}
