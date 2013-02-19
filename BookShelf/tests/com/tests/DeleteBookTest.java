package com.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.factory.HFactory;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.dao.Book;
import com.dao.Constants;
import com.dao.DAOApp;
import com.dao.DAOException;

public class DeleteBookTest {

	@Test
	public void deleteBookTest(){
		
		BasicConfigurator.configure();
		Cluster clstr = HFactory.getOrCreateCluster(Constants.CLUSTER_NAME, Constants.HOST_DEF+":9160");
		if( !clstr.describeKeyspace(Constants.KEYSPACE_NAME).equals(null)){
			clstr.dropKeyspace(Constants.KEYSPACE_NAME);
		}
		DAOApp dao = new DAOApp();
		Book beggining_state = new Book();
		try {
			beggining_state.newBook(117, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
			dao.addBook(beggining_state);
			dao.delBook(117);
			clstr.dropKeyspace(Constants.KEYSPACE_NAME);
		} catch (FileNotFoundException | DAOException e) {
				e.printStackTrace();
		}
	}
	
}
