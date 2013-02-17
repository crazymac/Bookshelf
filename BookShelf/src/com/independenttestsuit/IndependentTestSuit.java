package com.independenttestsuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.cassandraroutinetests.CreateColumnFamilyTest;
import com.cassandraroutinetests.CreateKeySpaceTest;
import com.cassandraroutinetests.TestParser;

@RunWith(Suite.class)
@SuiteClasses({CreateColumnFamilyTest.class, CreateKeySpaceTest.class, TestParser.class})
public class IndependentTestSuit {

}
