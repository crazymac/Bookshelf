package com.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.factory.HFactory;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.dao.Book;
import com.dao.Constants;
import com.dao.DAOApp;

public class GetAllRowKeysTest {
	
	@Test
	public void getIterList(){
		
		BasicConfigurator.configure();
		Cluster clstr = HFactory.getOrCreateCluster(Constants.CLUSTER_NAME, Constants.HOST_DEF+":9160");
		if(clstr.describeKeyspace(Constants.KEYSPACE_NAME) != null)
			clstr.dropKeyspace(Constants.KEYSPACE_NAME, true);
		DAOApp dao = new DAOApp();
		Book beggining_state = new Book();
		try {
			for(int i = 0; i< 40; ++i){
				
				beggining_state.newBook(i, new String("CassandraTest" + String.valueOf(i)), new String("Test" + String.valueOf(i)), new String("Tester" + String.valueOf(i)), new FileInputStream("resources/testbook"));
			}
			List<String> keys = dao.getAllRowKeys();
			for(String key: keys){
				System.out.println(key);
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}

}
