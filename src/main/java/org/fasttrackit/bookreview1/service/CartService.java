package org.fasttrackit.bookreview1.service;

import org.fasttrackit.bookreview1.domain.Book;
import org.fasttrackit.bookreview1.domain.Cart;
import org.fasttrackit.bookreview1.domain.User;
import org.fasttrackit.bookreview1.exception.ResourceNotFoundException;
import org.fasttrackit.bookreview1.persisteance.CartRepository;
import org.fasttrackit.bookreview1.transfer.AddBookToCartRequest;
import org.fasttrackit.bookreview1.transfer.BookInCartResponse;
import org.fasttrackit.bookreview1.transfer.CartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class CartService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CartService.class);


    private final UserService userService;
    private final BookService bookService;
    private final CartRepository cartRepository;

    @Autowired
    public CartService(UserService user, BookService bookService, CartRepository cartRepository) {

        this.userService = user;
        this.bookService = bookService;
        this.cartRepository = cartRepository;
    }
    @Transactional
    public void addBookToCart(AddBookToCartRequest request){
        LOGGER.info("Adding book to cart", request);
        Cart cart = cartRepository.findById(request.getUserId()).orElse(new Cart());
        if(cart.getUser() == null){
            LOGGER.debug(" cart does not exist, retrieving user to create a new cart", request.getUserId());
            User user = userService.getUser(request.getUserId());
            cart.setId(user.getId());
            cart.setUser(user);
        }
        Book book = bookService.getBook(request.getBookId());
        cart.addToCart(book);
        cartRepository.save(cart);
    }
    @Transactional
    public CartResponse getCart(long userId) {
        LOGGER.info(" retrieving cart for user " + userId);
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("there is no cart for user " + userId));
        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());

        Set<BookInCartResponse> booksInCart = new HashSet<>();

        Iterator<Book> iterator = cart.getBooks().iterator();

        while(iterator.hasNext()) {
            Book book = iterator.next();

            BookInCartResponse bookInCartResponse = new BookInCartResponse();
            bookInCartResponse.setId(book.getId());
            bookInCartResponse.setTitle(book.getTitle());
            bookInCartResponse.setPrice(book.getPrice());

            booksInCart.add(bookInCartResponse);
        }
        cartResponse.setBooks(booksInCart);
        return cartResponse;
    }





}
