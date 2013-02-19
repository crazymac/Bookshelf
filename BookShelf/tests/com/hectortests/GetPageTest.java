package com.hectortests;

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

public class GetPageTest {

	@SuppressWarnings("unused")
	@Test
	public void getpagesTest() {
		
		BasicConfigurator.configure();
		List<Book> pagedBookList = new ArrayList<Book>();
		
		Cluster clstr = HFactory.getOrCreateCluster(Constants.CLUSTER_NAME, Constants.HOST_DEF+":9160");
		if(clstr.describeKeyspace(Constants.KEYSPACE_NAME) != null)
			clstr.dropKeyspace(Constants.KEYSPACE_NAME, true);
		DAOApp dao = new DAOApp();
		Book beggining_state = new Book();
		try {
			for(int i = 0; i< 40; ++i){
				
				beggining_state.newBook(i, new String("CassandraTest" + String.valueOf(i)), new String("Test" + String.valueOf(i)), new String("Tester" + String.valueOf(i)), new FileInputStream("resources/testbook"));
				dao.addBook(beggining_state);
			}
			
			Assert.assertNotNull(dao.getAllBooks(2, 10));
			Assert.assertEquals(dao.getAllBooks(2, 10).size(), 10);
			
		} catch (FileNotFoundException | DAOException e) {e.printStackTrace();}
	}
		
}

