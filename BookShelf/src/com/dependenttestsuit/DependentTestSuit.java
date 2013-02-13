package com.dependenttestsuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bookshelftests.DAOConnTest;
import com.bookshelftests.InitCassSchTest;
import com.cassandraroutinetests.CfTests;
import com.cassandraroutinetests.CreateNewSchemaTest;
import com.cassandraroutinetests.TestConnectivity;
import com.cassandraroutinetests.TestKsCheck;

@RunWith(Suite.class)
@SuiteClasses({DAOConnTest.class, InitCassSchTest.class, CfTests.class, 
	TestConnectivity.class, TestKsCheck.class, CreateNewSchemaTest.class})
public class DependentTestSuit {

}
