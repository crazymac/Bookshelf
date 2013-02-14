package com.bookshelftests;


import java.io.FileInputStream;
import org.apache.cassandra.thrift.Cassandra;
import org.junit.Test;

import com.bookshelf.Book;
import com.bookshelf.DAOImpl;

public class AddBookTest {

	@Test
	public void addBookTest() throws Exception, Throwable{
		Book book1 = Book.getInstance();
		book1.newBook(1, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
		//Assert.assertNotNull(book1);
		Cassandra.Client cl = DAOImpl.getInstance().connClient();
		DAOImpl.getInstance().initCassandraSchema();
		DAOImpl.getInstance().addBook(book1);
		cl.system_drop_keyspace("BOOKKEYSPACE");
		DAOImpl.getInstance().closeConnection();
		
	}
	
}
