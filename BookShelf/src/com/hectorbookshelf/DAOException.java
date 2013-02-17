package com.hectorbookshelf;

import org.apache.log4j.Logger;

public class DAOException extends Exception {

	public static final Logger LOG = Logger.getLogger(DAOException.class);
	public DAOException(Exception e) {
		e.printStackTrace();
		LOG.debug(e);
	}

	public DAOException (String msg) {
		
		LOG.debug(msg);
		System.err.println(msg);
	}

	private static final long serialVersionUID = 1L;

}