package com.cassandraroutinetests;

import static org.junit.Assert.*;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.KsDef;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.cassandraroutine.Connector;
import com.cassandraroutine.HostPort;
import com.cassandraroutine.SchemaHandler;

public class CreateNewSchemaTest {
	
	@Test
	public void createNewSchemaTest() throws Exception, Throwable{
		
		BasicConfigurator.configure();
		HostPort hp = HostPort.getInstance();
		hp.hostPort = new String("localhost:9160");
		Cassandra.Client cl = new Cassandra.Client(Connector.getInstance().getConnection(hp));
		SchemaHandler.getInstance().createNewSchema(cl, "NEW");
		for(KsDef k:cl.describe_keyspaces()){
			assertNotSame(k.name,"NEW");
		}
		cl.system_drop_keyspace("NEW");
		Connector.getInstance().closeConnection();
	}
}