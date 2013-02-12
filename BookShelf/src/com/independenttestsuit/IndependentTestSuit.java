package com.independenttestsuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bookshelftests.BookConverterTest;
import com.bookshelftests.BookFormerTest;
import com.cassandraroutinetests.CreateColumnFamilyTest;
import com.cassandraroutinetests.CreateKeySpaceTest;
import com.cassandraroutinetests.TestParser;

@RunWith(Suite.class)
@SuiteClasses({BookConverterTest.class, BookFormerTest.class, CreateColumnFamilyTest.class, 
	CreateKeySpaceTest.class, TestParser.class})
public class IndependentTestSuit {

}
