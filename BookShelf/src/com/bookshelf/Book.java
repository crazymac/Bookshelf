package com.bookshelf;

import java.io.InputStream;

public class Book {

	private static class BookHolder{
		
		private static final Book newInstanceHolder = new Book();
	}
	
	public static Book getInstance(){
		
		return BookHolder.newInstanceHolder;
	}
	
	@Override
	public String toString(){
		
		return new String("Book id:" + getId() + " |Book Title:" + getTitle() + " |Book Author:" + getAuthor() + " |Book text:" + getText().getClass().getSimpleName());
	}
	
	public void newBook(int id, String title, String author, String genre, InputStream text){
		
		this.id=id;this.title=title;this.text=text;
		this.author=author;this.genre=genre;
	}
	
	private int id;
	private String title;
	private String author;
	private String genre;
	private InputStream text;

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getGenre() {
		return this.genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public InputStream getText() {
		return this.text;
	}
	public void setText(InputStream text) {
		this.text = text;
	}

}