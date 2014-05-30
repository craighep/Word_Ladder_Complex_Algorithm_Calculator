package uk.ac.aber.dcs.cs21120.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DataTest.class, ReadDictTest.class })
/**
 * Class used for running all the class tests available in the test package. Makes it easier 
 * to test all methods being tested at once.
 * @author Craig
 */
public class AllTests {

}
