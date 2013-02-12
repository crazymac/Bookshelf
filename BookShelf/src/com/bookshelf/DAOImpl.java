package com.bookshelf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnParent;
import org.apache.cassandra.thrift.ColumnPath;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KsDef;
import org.apache.cassandra.thrift.SchemaDisagreementException;
import org.apache.cassandra.thrift.TimedOutException;
import org.apache.cassandra.thrift.UnavailableException;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cassandraroutine.*;


public class DAOImpl implements DAO{

	private Connector conn;
	private Cassandra.Client client;
	public String bookKeySpace;
	public String bookColumnFamily;
	private CfDef bookCF;
	private KsDef bookKs;
	private static final Logger LOG = Logger.getLogger(DAOImpl.class);
	
	private static class DAOImplHolder{
		
		private static final DAOImpl newInstanceHolder = new DAOImpl();
	}
	
	public static DAOImpl getInstance(){
		
		return DAOImplHolder.newInstanceHolder;
	}
	
	
	
	public Cassandra.Client connClient(String HP) throws Exception, Throwable{

		HostPort.getInstance().hostPort = new String(HP);
		conn = Connector.getInstance();
		LOG.debug("["+new Date()+"] - Connection was established");
		this.client = conn.establishClient();
		LOG.debug("["+new Date()+"] - Client was connected");
		return this.client;	
	}
	
	public void setEmptyKeyspace(){
		
		SchemaHandler SH = SchemaHandler.getInstance();
		if(SH.ksCheck(this.client, bookKeySpace) == true){
			//bookKeySpace = keySpace;
			this.bookKs = SH.createKeySpace(bookKeySpace);
		}
		else{
			this.bookKs = SH.createKeySpace(bookKeySpace);
			//bookKeySpace = keySpace;
		}
	}

	public void setEmptyColumnFamily(){
		
		SchemaHandler SH = SchemaHandler.getInstance();
		try {
			this.client.set_keyspace(bookKeySpace);
			if(SH.cfCheck(this.client, bookColumnFamily) == true){
				this.bookCF = SH.createColumnFamily( bookColumnFamily, bookKeySpace);
				//bookColumnFamily = columnFamily;
			}
			else{
				
				this.bookCF = SH.createColumnFamily(bookColumnFamily, bookKeySpace);
				//bookColumnFamily = columnFamily;
			}
		}catch (InvalidRequestException | TException e) {
			LOG.debug("["+new Date()+"] - Inavalid request exception on navigating to Ks. RunTime Error MSG:" + e.getMessage());}
	}
	
	public void fillKeySpace(){
		
		try {
			setEmptyKeyspace();
			setEmptyColumnFamily();
			List<CfDef> defs = new ArrayList<CfDef>();
			defs.add(this.bookCF);
			this.bookKs.setCf_defs(defs);
			this.client.system_add_keyspace(this.bookKs);
			
		} catch (InvalidRequestException | SchemaDisagreementException
				| TException e) {
			LOG.debug("["+new Date()+"] - Inavalid request exception on filling new Ks. RunTime Error MSG:" + e.getMessage());
		}
	}
	
	@Override
	public int addBook(Book book) throws DAOException {
				
		try {
			this.client.set_keyspace(bookKeySpace);
		} catch (InvalidRequestException | TException e) {
			LOG.debug("["+new Date()+"] - Exception occured! RunTime Error MSG"+e.getMessage());}
		
		ColumnParent parent = new ColumnParent(bookColumnFamily);
		try {
			for(Column col:BookConverter.getInstance().book2row(book))
				this.client.insert(ByteBuffer.wrap(((String.valueOf(book.getId())).getBytes("UTF-8"))), parent, col, ConsistencyLevel.ONE);
		} catch (IOException | InvalidRequestException | UnavailableException
				| TimedOutException | TException e) {
			LOG.debug("["+new Date()+"] - Exception occured! Fail"+e.getMessage());
		}
		LOG.debug("["+new Date()+"] - Book: "+ book.getTitle()+" with id:"+book.getId()+" was added");
		return book.getId();
	}
	
	@Override
	public int delBook(int id) throws DAOException {
		
		ColumnPath path = new ColumnPath();
		path.column_family = bookColumnFamily;
		try {
			this.client.remove(ByteBuffer.wrap(((String.valueOf(id)).getBytes("UTF-8"))),path, System.currentTimeMillis(),ConsistencyLevel.ONE);
		} catch (UnsupportedEncodingException | InvalidRequestException
				| UnavailableException | TimedOutException | TException e) {
			LOG.debug("["+new Date()+"] - RunTime Exception occured. RunTime Exception MSG:" + e.getMessage());
		}
		LOG.debug("["+new Date()+"] - Book with id:"+id+" was deleted");
		return id;
	}

	@Override
	public List<Book> getAllBooks(int pageNum, int pageSize)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBookByTitle(int pageNum, int pageSize, String title)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBookByText(int pageNum, int pageSize, String text)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBookByAuthor(int pageNum, int pageSize, String author)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBookByGenre(int pageNum, int pageSize, String genre)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeSet<String> getAuthorByGenre(int pageNum, int pageSize,
			String genre) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeConnection() throws DAOException {
		
		if( conn.closeConnection())
			LOG.debug("["+new Date()+"] - Connection was flushed and closed. End session.");
		else
			LOG.debug("["+new Date()+"] - Exception while closing connection.");
	}

}
