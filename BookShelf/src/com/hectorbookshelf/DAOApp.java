package com.hectorbookshelf;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import me.prettyprint.cassandra.model.BasicColumnFamilyDefinition;
import me.prettyprint.cassandra.model.BasicKeyspaceDefinition;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;

public class DAOApp implements DAO{

	private static class DAOAppHolder{
		
		private static final DAOApp newInstanceHolder = new DAOApp();
	}
	
	public static DAOApp getInstance(){
		
		return DAOAppHolder.newInstanceHolder;
	}
	
	private Cluster clstr;
	private Keyspace ksOper;
	
	private DAOApp(){
		
		clstr = HFactory.getOrCreateCluster(Constants.CLUSTER_NAME, Constants.HOSTPORT_DEF);
		
		BasicKeyspaceDefinition KsDef = new BasicKeyspaceDefinition();
		KsDef.setName(Constants.KEYSPACE_NAME);
		KsDef.setDurableWrites(true);
		KsDef.setReplicationFactor(1);
		KsDef.setStrategyClass("org.apache.cassandra.locator.SimpleStrategy");
		clstr.addKeyspace(KsDef, true);
		ksOper = HFactory.createKeyspace(Constants.KEYSPACE_NAME, clstr);
				
		BasicColumnFamilyDefinition columnFamilyDefinition = new BasicColumnFamilyDefinition();
        columnFamilyDefinition.setKeyspaceName(Constants.KEYSPACE_NAME);
        columnFamilyDefinition.setName(Constants.CF_NAME);
        clstr.addColumnFamily(columnFamilyDefinition);
	}
	
	@Override
	public int addBook(Book book) throws DAOException {
		
		try{
			Mutator<String> intMut = HFactory.createMutator(ksOper, StringSerializer.get());
			intMut.insert("book"+ String.valueOf(book.getId()), Constants.CF_NAME, HFactory.createColumn("book id", book.getId()));
			intMut.insert("book"+ String.valueOf(book.getId()), Constants.CF_NAME, HFactory.createColumn("book title", book.getTitle()));
			intMut.insert("book"+ String.valueOf(book.getId()), Constants.CF_NAME, HFactory.createColumn("book author", book.getAuthor()));
			intMut.insert("book"+ String.valueOf(book.getId()), Constants.CF_NAME, HFactory.createColumn("book genre", book.getGenre()));
			byte[] toWrap = new byte[book.getText().available()];book.getText().read(toWrap);
			intMut.insert("book"+ String.valueOf(book.getId()), Constants.CF_NAME, HFactory.createColumn("book text", toWrap));
			
			clstr.getConnectionManager().shutdown();
		}catch (HectorException | IOException e) {
            e.printStackTrace();}
		return book.getId();
	}

	@Override
	public int delBook(int id) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		
	}

}
