package com.bookshelftests;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.bookshelf.DAOImpl;

public class InitCassSchTest {

	@Test
	public void testIt() throws Exception, Throwable{
		
		BasicConfigurator.configure();
		DAOImpl a = DAOImpl.getInstance();
		Cassandra.Client cl = a.connClient();
		a.initCassandraSchema();
		cl.system_drop_keyspace("BOOKKEYSPACE");
	}
	
}
