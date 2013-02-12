package com.cassandraroutinetests;

import static org.junit.Assert.*;

import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.thrift.TException;
import org.junit.Test;
import com.cassandraroutine.SchemaHandler;

public class CreateColumnFamilyTest {
	
	@Test
	public void createColumnFamilyTest() throws TException, TimedOutException{
		
		CfDef newCf = SchemaHandler.getInstance().createColumnFamily("Random", "Random");
		assertTrue(newCf.name.equals("Random"));
		assertTrue(newCf.getKeyspace().equals("Random"));
		assertNotNull(newCf);
	}

}
