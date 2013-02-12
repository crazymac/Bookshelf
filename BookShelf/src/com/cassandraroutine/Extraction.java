package com.cassandraroutine;

import org.apache.cassandra.thrift.*;
import org.apache.cassandra.thrift.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
@SuppressWarnings("unused")

public class Extraction {
	
	private static class ExtractionHolder{
		
		private static final Extraction InstanceHolder = new Extraction();
	}
	
	public static Extraction getInstance(){
		
		return ExtractionHolder.InstanceHolder;
	}

	
	public Column getSingleColumn(Cassandra.Client cl, String keySpace, String columnFamily, String columnPath) 
	throws UnsupportedEncodingException, InvalidRequestException, NotFoundException, 
	UnavailableException, TimedOutException, TException{
		
		cl.set_keyspace(keySpace);
		ColumnPath path = new ColumnPath(columnFamily);
		path.setColumn(ByteBuffer.wrap(columnPath.getBytes("UTF-8")));
		return (cl.get(ByteBuffer.wrap("1".getBytes("UTF-8")), path, ConsistencyLevel.ONE)).column;
		
	}
	
	public void getDataFromColumn(Column col){
		
		System.out.println("Column name: " + new String(col.getName()) + " Column Value: " + new String(col.getValue()).toString() + " Timestamp: " + col.getTimestamp());
	}
	
}
