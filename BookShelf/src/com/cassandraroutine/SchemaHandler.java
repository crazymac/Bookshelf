package com.cassandraroutine;

import java.util.*;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KsDef;
import org.apache.cassandra.thrift.SchemaDisagreementException;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;


public class SchemaHandler {
	
	
	private static final Logger LOG = Logger.getLogger(SchemaHandler.class);
	
	private static class SchemaHandlerHolder{
		
		private static final SchemaHandler InstanceHolder = new SchemaHandler();
	}
	
	public static SchemaHandler getInstance(){
		
		return SchemaHandlerHolder.InstanceHolder;
	}
	
	public boolean ksCheck(Cassandra.Client cl, String ksName){
		
		try {
			List<KsDef> defs = cl.describe_keyspaces();
			for(KsDef ks: defs){
				if(ks.name.equals(ksName) == true)
					return true;
				else
					return false;
			}
		} catch (InvalidRequestException | TException e) {
			LOG.debug("["+new Date()+"] - Inavalid request exception on keyspaces check. RunTime Error MSG:" + e.getMessage());
		}
		return true;
	}
	
	public boolean cfCheck(Cassandra.Client cl, String cfName){
		
		try {
			List<KsDef> defs = cl.describe_keyspaces();
			for(KsDef ks: defs){
				
				List<CfDef> newCFs = ks.getCf_defs();
				for(CfDef cf: newCFs){
					if(cf.name.equals(cfName))
						return true;
					else
						return false;
				}
			}
		} catch (InvalidRequestException | TException e) {
			LOG.debug("["+new Date()+"] - Inavalid request exception on column family check. RunTime Error MSG:" + e.getMessage());
		}
		return true;
	}
	
	public KsDef createKeySpace(String ksName){
		
		KsDef newKs = new KsDef();
		newKs.setName(ksName);
		newKs.setReplication_factor(1);
		newKs.setStrategy_class("org.apache.cassandra.locator.NetworkTopologyStrategy");
		
		return newKs;
	}
	
	public CfDef createColumnFamily(String cfName, String ksName){
		
		CfDef newCf = new CfDef();
		newCf.setName(cfName);
		newCf.setKeyspace(ksName);
		return newCf;
	}
	
	public void createNewSchema(Cassandra.Client cl, String newKsName){
		
		try {
			KsDef newKs = createKeySpace(newKsName);
			List<CfDef> newCFs = new ArrayList<CfDef>();
			newKs.setCf_defs(newCFs);
			cl.system_add_keyspace(newKs);
		} catch (InvalidRequestException | SchemaDisagreementException
				| TException e) {
			LOG.debug("["+new Date()+"] - Inavalid request exception on adding new CFs to Ks. RunTime Error MSG:" + e.getMessage());
		}
	}
	
	public void addCf2Ks(Cassandra.Client cl, String newCfName, String existedKs){
		
		List<CfDef> newCFs = new ArrayList<CfDef>();
		newCFs.add(createColumnFamily(newCfName, existedKs));
		try {
			cl.set_keyspace(existedKs);
			for(CfDef newCF: newCFs){
				
				cl.system_add_column_family(newCF);
			}
		} catch (InvalidRequestException | TException e) {
			LOG.debug("["+new Date()+"] - Inavalid request exception on navigating to Ks. RunTime Error MSG:" + e.getMessage());}
		catch (SchemaDisagreementException e) {
			LOG.debug("["+new Date()+"] - Inavalid request exception on adding new345 CFs to Ks. RunTime Error MSG:" + e.getMessage());}
	}
		
}

