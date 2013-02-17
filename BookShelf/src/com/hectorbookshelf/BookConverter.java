package com.hectorbookshelf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;

import org.apache.cassandra.thrift.Column;

public class BookConverter {

	private static class BookConverterHolder{
		
		private static final BookConverter InstanceHolder = new BookConverter();
	}
	
	public static BookConverter getInstance(){
		
		return BookConverterHolder.InstanceHolder;
	}	
	
	@SuppressWarnings("rawtypes")
	public List<HColumn> book2row(Book newBook) throws IOException{
		
		List<HColumn> cols = new ArrayList<HColumn>();
			
		cols.add(HFactory.createColumn("book id", newBook.getId()));
		cols.add(HFactory.createColumn("book title", newBook.getTitle()));
		cols.add(HFactory.createColumn("book author", newBook.getAuthor()));
		cols.add(HFactory.createColumn("book genre", newBook.getGenre()));
		byte[] toWrap = new byte[newBook.getText().available()];newBook.getText().read(toWrap);
		cols.add(HFactory.createColumn("book text", toWrap));

		return cols;
	}
	
	public Book row2book(List<Column> book){
		
		Book newBook = Book.getInstance();
		
		for(Column col: book){
			if(new String(col.getName()).equals("id")){
				
				newBook.setId(Integer.parseInt(new String(col.getValue())));
			}
			
			if(new String(col.getName()).equals("title")){
				
				newBook.setTitle(new String(col.getValue()));
			}
			
			if(new String(col.getName()).equals("author")){
				
				newBook.setAuthor(new String(col.getValue()));
			}
			
			if(new String(col.getName()).equals("genre")){
				
				newBook.setGenre(new String(col.getValue()));
			}
			
			if(new String(col.getName()).equals("text")){
				
				InputStream is = new ByteArrayInputStream(col.getValue());
				newBook.setText(is);
			}
		}
		return newBook;
	}
	
	public List<String> getBookTokens(){
		
		List<String> book = new ArrayList<String>();
		book.add("id");book.add("title");book.add("author");book.add("genre");book.add("text");
		return book;
	}
	
}
