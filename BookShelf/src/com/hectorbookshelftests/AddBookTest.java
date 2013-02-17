package com.hectorbookshelftests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.factory.HFactory;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import com.hectorbookshelf.Book;
import com.hectorbookshelf.Constants;
import com.hectorbookshelf.DAOApp;
import com.hectorbookshelf.DAOException;

public class AddBookTest {
	
	@Test
	public void addBookTest(){
		
		BasicConfigurator.configure();
		Cluster clstr = HFactory.getOrCreateCluster(Constants.CLUSTER_NAME, Constants.HOSTPORT_DEF);
		clstr.dropKeyspace(Constants.KEYSPACE_NAME);
		DAOApp dao = DAOApp.getInstance();
		Book beggining_state = Book.getInstance();
		try {
			beggining_state.newBook(117, "CassandraTest", "Test", "Tester", new FileInputStream("resources/testbook"));
			dao.addBook(beggining_state);
		} catch (FileNotFoundException | DAOException e) {
				e.printStackTrace();
		}

	}

}
