package org.fasttrackit.bookreview1;

import org.fasttrackit.bookreview1.domain.Book;
import org.fasttrackit.bookreview1.exception.ResourceNotFoundException;
import org.fasttrackit.bookreview1.service.BookService;
import org.fasttrackit.bookreview1.steps.BookSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

}
