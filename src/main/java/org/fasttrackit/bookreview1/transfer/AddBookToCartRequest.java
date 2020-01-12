package org.fasttrackit.bookreview1.transfer;
import javax.validation.constraints.NotNull;

public class AddBookToCartRequest {

    @NotNull
    private Long UserId;
    @NotNull
    private Long bookId;

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
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
                "UserId=" + UserId +
                ", bookId=" + bookId +
                '}';
    }
}
