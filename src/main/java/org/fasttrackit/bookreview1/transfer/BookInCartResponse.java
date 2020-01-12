package org.fasttrackit.bookreview1.transfer;
import java.util.Objects;

public class BookInCartResponse {
    private Double price;
    private String title;
    private Long id;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookInCartResponse{" +
                "price=" + price +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookInCartResponse that = (BookInCartResponse) o;
        return Objects.equals(price, that.price) &&
                Objects.equals(title, that.title) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, title, id);
    }
}