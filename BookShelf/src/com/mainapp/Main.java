package com.mainapp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.hectorbookshelf.Book;
import com.hectorbookshelf.DAOApp;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception, Throwable {
		
		DAOApp dao = new DAOApp();
		List<Book> before = new ArrayList<Book>();
		List<Book> after = new ArrayList<Book>();
		Book bstate = new Book();
		for(int i = 0; i< 40; ++i){
			
			bstate.newBook(i, new String("CassandraTest" + String.valueOf(i)), new String("Test" + String.valueOf(i)), new String("Tester" + String.valueOf(i)), new FileInputStream("resources/testbook"));
			before.add(bstate);
			dao.addBook(bstate);
		}
		after = dao.getAllBooks(1, 40);
		
		after = dao.getBookByTitle(1, 40, new String("CassandraTest" + String.valueOf(5)));
		
    }
}

