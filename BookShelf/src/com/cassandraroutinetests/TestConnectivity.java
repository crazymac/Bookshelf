package com.cassandraroutinetests;

import static org.junit.Assert.*;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.junit.Test;

import com.cassandraroutine.Connector;
import com.cassandraroutine.HostPort;
@SuppressWarnings("unused")
public class TestConnectivity {

	@Test
	public void testConnectorObj(){
		
		BasicConfigurator.configure();
		HostPort HP = HostPort.getInstance();
		HP.hostPort = new String("localhost:9160");
		Connector conn = Connector.getInstance();
		assertNotNull("Error in thrift protocol implementing", conn);
	}
	
	@Test
	public void testOnOpeningConnection(){
		
		BasicConfigurator.configure();
		HostPort HP = HostPort.getInstance();
		HP.hostPort = new String("localhost:9160");
		try {
			TProtocol tProt = Connector.getInstance().getConnection(HP);
			assertNotNull("Error in thrift protocol implementing", tProt);
		} catch (TimedOutException |TException e) {	}
	}
	
	@Test
	public void testClientConnectivity(){
		
		BasicConfigurator.configure();
		HostPort HP = HostPort.getInstance();
		HP.hostPort = new String("localhost:9160");
		try {
			Cassandra.Client cl = Connector.getInstance().establishClient();
			assertNotNull("Error while client connection establishing", cl);
		} catch (TimedOutException |TException e) {	}
	}
	
	@Test
	public void testOnClosingConnection() throws TException, TimedOutException{
	
		BasicConfigurator.configure();
		HostPort HP = HostPort.getInstance();
		HP.hostPort = new String("localhost:9160");
		Connector conn = Connector.getInstance();
		TProtocol tProt = Connector.getInstance().getConnection(HP);
		boolean flg = conn.closeConnection();
		assertTrue("Error while client connection establishing", flg);
	}
}
