package com.cassandraroutinetests;

import static org.junit.Assert.*;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.SchemaDisagreementException;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.TException;
import org.junit.Test;

import com.cassandraroutine.Connector;
import com.cassandraroutine.HostPort;
import com.cassandraroutine.SchemaHandler;

public class CfTests {
	
	@Test
	public void cfCheckTest() throws TException, TimedOutException, InvalidRequestException, SchemaDisagreementException{
		BasicConfigurator.configure();
		HostPort hp = HostPort.getInstance();
		hp.hostPort = new String("localhost:9160");
		Cassandra.Client cl = new Cassandra.Client(Connector.getInstance().getConnection(hp));
		SchemaHandler.getInstance().createNewSchema(cl, "NEW");
		SchemaHandler.getInstance().addCf2Ks(cl, "cf", "NEW");
		
		assertTrue(SchemaHandler.getInstance().cfCheck(cl, "cf"));		
		cl.system_drop_keyspace("NEW");
		Connector.getInstance().closeConnection();}
		
}
