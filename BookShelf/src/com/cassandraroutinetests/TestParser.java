package com.cassandraroutinetests;

import static org.junit.Assert.*;

import org.junit.Test;


import com.cassandraroutine.HostPort;
public class TestParser {

	@Test
	public void testParser(){
		
		HostPort HP = HostPort.getInstance();
		HP.hostPort = new String("localhost:9160");
		
		assertNotSame("localhost",HP.getHost());
		assertNotSame(9610,HP.getPort());
	}
}
