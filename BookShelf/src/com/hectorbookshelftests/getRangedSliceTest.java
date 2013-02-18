package com.hectorbookshelftests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.factory.HFactory;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.hectorbookshelf.Book;
import com.hectorbookshelf.Constants;
import com.hectorbookshelf.DAOApp;
import com.hectorbookshelf.DAOException;

public class getRangedSliceTest {

	@Test
	public void getRangedSlicesTest(){
		
		BasicConfigurator.configure();
		List<Book> before = new ArrayList<Book>();
		List<Book> after = new ArrayList<Book>();
		
		Cluster clstr = HFactory.getOrCreateCluster(Constants.CLUSTER_NAME, Constants.HOST_DEF+":9160");
		if( !clstr.describeKeyspace(Constants.KEYSPACE_NAME).equals(null)){
			clstr.dropKeyspace(Constants.KEYSPACE_NAME);
		}
		DAOApp dao = new DAOApp();
		Book beggining_state = Book.getInstance();
		try {
			for(int i = 0; i< 40; ++i){
				
				beggining_state.newBook(i, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
				dao.addBook(beggining_state);
				before.add(beggining_state);
			}
			
			after = dao.getAllBooks(1, 40);
			
			Assert.assertTrue(before.equals(after));
		} catch (FileNotFoundException | DAOException e) {e.printStackTrace();}
	}
}
