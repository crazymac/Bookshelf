package com.cassandraroutine;
import java.util.Date;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.cassandra.thrift.Cassandra;
@SuppressWarnings("unused")
public class Connector {
		
	private TTransport tTrans;
	public static final Logger LOG = Logger.getLogger(Connector.class);
	
	private static class ConnectorHolder{
	
		public static final Connector newInstanceHolder = new Connector();
	}
	
	public static Connector getInstance(){
		
		return ConnectorHolder.newInstanceHolder;
	}
	
	public TProtocol getConnection(HostPort HP) 
			throws TException, TimedOutException{
		
		TFastFramedTransport trans = new TFastFramedTransport(new TSocket(HP.getHost(),HP.getPort()));
		TProtocol prot = new TBinaryProtocol(trans);
		this.tTrans = trans;
		trans.open();
		LOG.debug("["+new Date()+"] - Connection to Cassandra established");
		return prot;
	}
	
	public Cassandra.Client establishClient() throws TException, TimedOutException{
		
		return  new Cassandra.Client(getConnection(HostPort.getInstance()));
	}
	
	public boolean closeConnection(){

		try {
			this.tTrans.flush();
			this.tTrans.close();
			LOG.debug("["+new Date()+"] - Connection to Cassandra droped successfuly");
			return true;
		} catch (TTransportException e) {e.printStackTrace();}
		return false;
	}

}


