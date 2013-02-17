package com.hectorbookshelf;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import me.prettyprint.cassandra.model.BasicColumnFamilyDefinition;
import me.prettyprint.cassandra.model.BasicKeyspaceDefinition;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.exceptions.HectorException;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
@SuppressWarnings("unchecked")

public class DAOApp implements DAO{

	private Cluster clstr;
	private Keyspace ksOper;
	
	public DAOApp(){
		
		clstr = HFactory.getOrCreateCluster(Constants.CLUSTER_NAME, Constants.HOST_DEF+":9160");
		
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

	@SuppressWarnings("rawtypes")
	@Override
	public int addBook(Book book) throws DAOException {
		
		try{
			Mutator<String> mutator = HFactory.createMutator(ksOper, StringSerializer.get());
			for(HColumn col: BookConverter.getInstance().book2row(book)){
				mutator.insert("book"+ String.valueOf(book.getId()), Constants.CF_NAME, col);
			}
		}catch (HectorException | IOException e) {
            e.printStackTrace();}
		return book.getId();
	}

	@Override
	public int delBook(int id) throws DAOException {
		Mutator<String> mutator = HFactory.createMutator(ksOper, StringSerializer.get());
		mutator.delete("book"+String.valueOf(id), Constants.CF_NAME, null, StringSerializer.get()); ;
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
