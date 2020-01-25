package org.fasttrackit.bookreview1.transfer;
import javax.validation.constraints.NotNull;

public class AddBookToCartRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long bookId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
       this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "AddBookToCartRequest{" +
                "UserId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
