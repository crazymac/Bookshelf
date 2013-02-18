package com.hectorbookshelftests;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import me.prettyprint.hector.api.beans.HColumn;
import org.junit.Test;
import com.hectorbookshelf.*;

public class bookConvTest {

	public Book bstate;
	public Book fstate;
	public List<HColumn<String, String>> cols;
	
	public void setUp(){
		
		bstate = Book.getInstance();
		fstate = Book.getInstance();
		cols = new ArrayList<HColumn<String, String>>();
	}
	
	@Test
	public void book2rowTest(){
			
		try {
			setUp();
			bstate.newBook(117, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
			
			
			cols = BookConverter.getInstance().book2row(bstate);
			Assert.assertNotNull(cols);
			
		} catch (Exception e) {
			e.printStackTrace();}
	}
	
	@Test
	public void row2bookTest(){
		
		setUp();
		try {
			bstate.newBook(117, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
			
			cols = BookConverter.getInstance().book2row(bstate);
			fstate = BookConverter.getInstance().row2book(cols);
			Assert.assertTrue(bstate.equals(fstate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
