package org.fasttrackit.bookreview1;

import org.fasttrackit.bookreview1.domain.Book;
import org.fasttrackit.bookreview1.exception.ResourceNotFoundException;
import org.fasttrackit.bookreview1.service.BookService;
import org.fasttrackit.bookreview1.steps.BookSteps;
import org.fasttrackit.bookreview1.transfer.BookResponse;
import org.fasttrackit.bookreview1.transfer.GetBookRequest;
import org.fasttrackit.bookreview1.transfer.SaveBookRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)

@SpringBootTest
public class BookServiceIntegrationTests {
	@Autowired
	private BookService bookService;
	@Autowired
	private BookSteps bookSteps;
	@Test
	public void TestCreateBook_whenValidRequest_thenReturnCreatedBook(){
		bookSteps.createBook();
	}
	@Test
	public void TestGetBook_whenExistingEntity_thenReturnBook(){
		Book createdBook = bookSteps.createBook();
		Book retrievedBook = bookService.getBook(createdBook.getId());

		assertThat(retrievedBook, notNullValue());
		assertThat(retrievedBook.getId(), is(createdBook.getId()));
		assertThat(retrievedBook.getTitle(),is(createdBook.getTitle()));
		assertThat(retrievedBook.getType(), is(createdBook.getType()));
		assertThat(retrievedBook.getAuthor(), is(createdBook.getAuthor()));
		assertThat(retrievedBook.getYearOfRelease(), is(createdBook.getYearOfRelease()));
		assertThat(retrievedBook.getLanguage(), is(createdBook.getLanguage()));
		assertThat(retrievedBook.getPages(), is(createdBook.getPages()));
		assertThat(retrievedBook.getDescription(), is(createdBook.getDescription()));
		assertThat(retrievedBook.getImagePath(), is(createdBook.getImagePath()));
		assertThat(retrievedBook.getPrice(), is(createdBook.getPrice()));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void TestGetBookById_whenNonExistingEntity_thenThrowNotFoundException(){
		bookService.getBook(99999999L);
	}
	@Test
	public void TestGetBooksByTitleAndAuthor_whenValidRequest(){
		Book createdBook = bookSteps.createBook();
		GetBookRequest request= new GetBookRequest();
		request.setPartialAuthor(createdBook.getAuthor());
		request.setPartialTitle(createdBook.getTitle());


		Page<BookResponse> books = bookService.getBooks(request, Pageable.unpaged());

		assertThat(books.iterator().next().getAuthor(), is(createdBook.getAuthor()));
		assertThat(books.iterator().next().getTitle(), is(createdBook.getTitle()));
	}
	@Test
	public void TestUpdateBook_whenValidRequest_thenReturnUpdatedProduct(){
		Book createdBook = bookSteps.createBook();

		SaveBookRequest request = new SaveBookRequest();
		request.setYearOfRelease(createdBook.getYearOfRelease() + 2000);
		request.setLanguage(createdBook.getLanguage() + "update");
		request.setPages(createdBook.getPages() + 2000);
		request.setTitle(createdBook.getTitle() + "updated");
		request.setAuthor(createdBook.getAuthor() + "updated");
		request.setDescription(createdBook.getDescription() + "updated");
		request.setImagePath(createdBook.getImagePath() + "updated");
		request.setType(createdBook.getType() + "updated");
		request.setPrice(createdBook.getPrice() + 10);

		Book updatedBook = bookService.updateBook(createdBook.getId(), request);
		assertThat(updatedBook, notNullValue());
		assertThat(updatedBook.getId(), notNullValue());
		assertThat(" unexpected Book Title ", updatedBook.getTitle(), is(request.getTitle()));
		assertThat(" unexpected Year ", updatedBook.getYearOfRelease(), is(request.getYearOfRelease()));
		assertThat(" unexpected Language ", updatedBook.getLanguage(), is(request.getLanguage()));
		assertThat(" unexpected Page number ", updatedBook.getPages(), is(request.getPages()));
		assertThat(" unexpected Author ", updatedBook.getAuthor(), is(request.getAuthor()));
		assertThat(" unexpected Description ", updatedBook.getDescription(), is(request.getDescription()));
		assertThat(" unexpected Type ", updatedBook.getType(), is(request.getType()));
		assertThat(" unexpected ImagePath", updatedBook.getImagePath(), is(request.getImagePath()));
		assertThat(" unexpected price", updatedBook.getPrice(), is(request.getPrice()));
	}

	@Test (expected = ResourceNotFoundException.class)
	public void TestDeleteBook_whenValidRequest_thenThrowResourceNotFound(){
		Book createdBook = bookSteps.createBook();
		Book createdBook1 = bookSteps.createBook();
		bookService.deleteBook(createdBook.getId());
		bookService.getBook(createdBook.getId());
	}

}
