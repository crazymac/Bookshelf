package com.cassandraroutinetests;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import com.bookshelf.DAOImpl;
import com.cassandraroutine.Connector;
import com.cassandraroutine.SchemaHandler;

public class CreateNewSchemaTest {
	
	@Test
	public void createNewSchemaTest() throws Exception, Throwable{
		
		BasicConfigurator.configure();
		DAOImpl a = DAOImpl.getInstance();
		Cassandra.Client cl = a.connClient();
		SchemaHandler.getInstance().createNewSchema(cl, "bookKeySpace");
		SchemaHandler.getInstance().addCf2Ks(cl, "bookColumnFamily", "bookKeySpace");
		cl.system_drop_keyspace("bookKeySpace");
		Connector.getInstance().closeConnection();
	}
}