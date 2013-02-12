package com.mainapp;

import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.SchemaDisagreementException;
import org.apache.log4j.BasicConfigurator;
import org.apache.thrift.TException;

import com.cassandraroutine.*;
import com.bookshelf.*;
@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws InvalidRequestException, SchemaDisagreementException, TException{

		BasicConfigurator.configure();
		
	}

}
