package com.bookshelf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.cassandra.thrift.Column;

@SuppressWarnings("static-access")
public class BookConverter {

	private static class BookConverterHolder{
		
		private static final BookConverter InstanceHolder = new BookConverter();
	}
	
	public static BookConverter getInstance(){
		
		return BookConverterHolder.InstanceHolder;
	}	
	
	public List<Column> book2row(Book newBook) throws IOException{
		
		List<Column> cols = new ArrayList<Column>();
		cols.add(formColumn("id",ByteBuffer.allocate(4).putInt(newBook.getId())));
		cols.add(formColumn("title",ByteBuffer.wrap(newBook.getTitle().getBytes("UTF-8"))));
		cols.add(formColumn("author",ByteBuffer.wrap(newBook.getAuthor().getBytes("UTF-8"))));
		cols.add(formColumn("genre",ByteBuffer.wrap(newBook.getGenre().getBytes("UTF-8"))));
		byte[] toWrap = new byte[newBook.getText().available()];newBook.getText().read(toWrap);
		cols.add(formColumn("text",ByteBuffer.wrap(toWrap)));
		
		return cols;
	}
	
	public Book row2book(List<Column> book){
		
		Book newBook = Book.getInstance();
		
		for(Column col: book){
			if(new String(col.getName()).equals("id")){
				ByteBuffer buf = ByteBuffer.allocate(4).wrap(col.getValue());
				buf.flip();
				newBook.setId( buf.getInt() );
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
				
				newBook.setText(new ByteArrayInputStream(col.getValue()));
			}
		}
		return newBook;
	}
	
	private Column formColumn(String token, ByteBuffer data) 
			throws UnsupportedEncodingException{
		
		Column temp = new Column();
		temp.setName(ByteBuffer.wrap(token.getBytes("UTF8")));
		temp.setValue(data);
		temp.setTimestamp(System.currentTimeMillis());
		return temp;
	}
	
	
}
