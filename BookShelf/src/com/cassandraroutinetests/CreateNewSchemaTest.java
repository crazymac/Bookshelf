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
		SchemaHandler sh = SchemaHandler.getInstance();
		DAOImpl a = DAOImpl.getInstance();
		Cassandra.Client cl = a.connClient();
		sh.createNewSchema(cl, "bookKeySpace");
		sh.addCfs2Ks(cl, "bookColumnFamily".concat(String.valueOf(1)), "bookKeySpace");
		sh.addCfs2Ks(cl, "bookColumnFamily".concat(String.valueOf(2)), "bookKeySpace");
		cl.system_drop_keyspace("bookKeySpace");
		Connector.getInstance().closeConnection();
	}
}