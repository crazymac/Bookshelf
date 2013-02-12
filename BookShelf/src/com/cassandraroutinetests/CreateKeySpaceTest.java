package com.cassandraroutinetests;

import static org.junit.Assert.*;

import org.apache.cassandra.thrift.KsDef;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.thrift.TException;
import org.junit.Test;
import com.cassandraroutine.SchemaHandler;

public class CreateKeySpaceTest {
	
	@Test
	public void createKeySpaceTest() throws TException, TimedOutException{
		
		KsDef newKs = SchemaHandler.getInstance().createKeySpace("Random");
		assertNotNull(newKs);
		assertTrue(newKs.name.equals("Random"));
	}

}
