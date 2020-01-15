package org.fasttrackit.bookreview1.web;

import org.fasttrackit.bookreview1.domain.Book;
import org.fasttrackit.bookreview1.service.BookService;
import org.fasttrackit.bookreview1.transfer.BookResponse;
import org.fasttrackit.bookreview1.transfer.GetBookRequest;
import org.fasttrackit.bookreview1.transfer.SaveBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid SaveBookRequest request) {
        Book book = bookService.createBook(request);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
        Book book = bookService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getBooks(GetBookRequest request, Pageable pageable){
        Page<BookResponse> books = bookService.getBooks(request, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
//    public  ResponseEntity<Page<ProductResponse>> getProducts(GetProductsRequest request, Pageable pageable){
//        Page<ProductResponse> products = productService.getProducts(request, pageable);
//        return  new ResponseEntity<>(products, HttpStatus.OK);
//

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody SaveBookRequest request){
        Book book = bookService.updateBook(id, request);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
