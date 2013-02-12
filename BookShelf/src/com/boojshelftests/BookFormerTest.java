package com.boojshelftests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.junit.Test;

import com.bookshelf.Book;
public class BookFormerTest {
	
	public Book book, book1;
	@Test
	public void formBookTest() throws FileNotFoundException{
		
		book = Book.getInstance();
		book1 = Book.getInstance();
		book1.newBook(1, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
		book.setId(1);
		book.setAuthor("Test");
		book.setGenre("Tester");
		book.setTitle("CassandraTest");
		book.setText(new FileInputStream("resources/testbook"));
		assertEquals("Same", book, book1);
	}
	//@Ignore
	@Test
	public void toStringTest() throws FileNotFoundException{
		
		book = Book.getInstance();
		book1 = Book.getInstance();
		book1.newBook(1, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
		book.setId(1);
		book.setAuthor("Test");
		book.setGenre("Tester");
		book.setTitle("CassandraTest");
		book.setText(new FileInputStream("resources/testbook"));
		assertNotSame(book.toString(), book1.toString());
	}
	
}
