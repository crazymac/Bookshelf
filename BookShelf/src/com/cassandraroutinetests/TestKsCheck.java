package com.cassandraroutinetests;

import static org.junit.Assert.*;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.TException;
import org.junit.Test;

import com.cassandraroutine.*;

public class TestKsCheck {
	
	@Test
	public void ksCheckTest() throws InvalidRequestException{
		
		try {
			BasicConfigurator.configure();
			HostPort hp = HostPort.getInstance();
			hp.hostPort = new String("localhost:9160");
			assertTrue(SchemaHandler.getInstance().ksCheck(new Cassandra.Client(Connector.getInstance().getConnection(hp)),"system"));
			Connector.getInstance().closeConnection();
		} catch (TException | TimedOutException e){}
	}
}
