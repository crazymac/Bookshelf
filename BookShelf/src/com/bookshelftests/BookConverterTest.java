package com.bookshelftests;

import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.cassandra.thrift.Column;
import org.junit.Test;

import com.bookshelf.Book;
import com.bookshelf.BookConverter;

public class BookConverterTest {

	public Book beggining_state;
	public Book final_state;
	public List<Column> rowBook;
	
	public void setUp() throws FileNotFoundException {
		
		beggining_state = Book.getInstance();
		final_state = Book.getInstance();
		beggining_state.newBook(117, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
		rowBook = new ArrayList<Column>();
		
	}

	@Test
	public void book2row2bookConverterTest() throws IOException{
		
		setUp();
		rowBook = BookConverter.getInstance().book2row(beggining_state);
		final_state = BookConverter.getInstance().row2book(rowBook);
		assertEquals(beggining_state, final_state);
	}
}
