package com.dependenttestsuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.cassandraroutinetests.CfTests;
import com.cassandraroutinetests.CreateNewSchemaTest;
import com.cassandraroutinetests.TestConnectivity;
import com.cassandraroutinetests.TestKsCheck;

@RunWith(Suite.class)
@SuiteClasses({ CfTests.class,	TestConnectivity.class, TestKsCheck.class, CreateNewSchemaTest.class})
public class DependentTestSuit {}
