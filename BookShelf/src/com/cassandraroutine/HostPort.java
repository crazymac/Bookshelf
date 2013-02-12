package com.cassandraroutine;


public class HostPort{

	private static class HostPortHolder{
		
		private static final HostPort InstanceHolder = new HostPort();
	}
	
	public static HostPort getInstance(){
		
		return (HostPortHolder.InstanceHolder);
	}
	
	public String hostPort;
	
	public int getPort(){
		
		return Integer.parseInt(((hostPort.split(":"))[1]));
	}
	
	public String getHost(){
		
		return (hostPort.split(":"))[0];
	}
}
