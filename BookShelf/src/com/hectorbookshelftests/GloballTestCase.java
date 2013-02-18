package com.hectorbookshelftests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddBookTest.class, bookConvTest.class, DeleteBookTest.class,
		getBookByTests.class, getRangedSliceTest.class })
public class GloballTestCase {

}
