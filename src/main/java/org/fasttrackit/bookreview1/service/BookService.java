package org.fasttrackit.bookreview1.service;

import org.fasttrackit.bookreview1.domain.Book;
import org.fasttrackit.bookreview1.exception.ResourceNotFoundException;
import org.fasttrackit.bookreview1.persisteance.BookRepository;
import org.fasttrackit.bookreview1.transfer.GetBookRequest;
import org.fasttrackit.bookreview1.transfer.SaveBookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Book getBook(long id) {
        LOGGER.info("Retrieving Book ", id);
        return bookRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Book " + id + " not found" ));
    }

    public Page<Book> getBooks (GetBookRequest request, Pageable pageable){
        LOGGER.info("retrieving products: {}", request);

        if(request != null && request.getpartialTitle() !=null && request.getPartialAuthor() != null) {
            return bookRepository.findByTitleAndAuthor(request.getpartialTitle(), request.getPartialAuthor(), pageable);
        }
        else{
            return bookRepository.findByTitleOrAuthor(request.getPartialAuthor(), pageable);
        }
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
