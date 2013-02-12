package com.boojshelftests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.bookshelf.Book;

public class BookConverterTest {

	@Test
	public void converterTest() throws FileNotFoundException{
		
		Book book = Book.getInstance();
		book.newBook(1, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
	}
}
