package com.bookshelftests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bookshelf.DAOImpl;

public class DAOConnTest {
	
	@Test
	public void testConn() throws Exception, Throwable{
		
		String HP = new String("localhost:9160");
		assertNotNull(DAOImpl.getInstance().connClient(HP));
		DAOImpl.getInstance().closeConnection();
		
	}

}
