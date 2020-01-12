package org.fasttrackit.bookreview1.transfer;

import java.util.HashSet;
import java.util.Set;

public class CartResponse {
    private Long id;
    private Set<BookInCartResponse> books = new HashSet<>();

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Set<BookInCartResponse> getBooks() {return books;}
    public void setBooks(Set<BookInCartResponse> books) {this.books = books;}

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", books=" + books +
                '}';
    }
}
