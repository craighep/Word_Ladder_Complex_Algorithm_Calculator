/**
 * 
 */
package uk.ac.aber.dcs.cs21120.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.cs21120.data.Data;
import uk.ac.aber.dcs.cs21120.data.ReadDict;
/**
 * Tests created for the Data class of program. Tested by inserting random data into the set()
 * method and then used an assertEquals test in the get() method to check data is being set 
 * properly
 * @author Craig
 *
 */
public class DataTest {
	Data Data;
	ReadDict ReadDict;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Data = new Data();
		ReadDict = new ReadDict();
		ArrayList<String> strlist = new ArrayList<String>();
		strlist.add("sdfs1");
	    Data.setDict(strlist );
	}

	/**
	 * Test method for {@link uk.ac.aber.dcs.cs21120.data.Data#setDict(java.util.List)}.
	 * @throws IOException 
	 */
	@Test
	public void testSetDict() throws IOException {
		ArrayList<String> strlist = new ArrayList<String>();
	    strlist.add("sdfs1");
	    Data.setDict(strlist );
	  
	}

	/**
	 * Test method for {@link uk.ac.aber.dcs.cs21120.data.Data#getDict()}.
	 */
	@Test
	public void testGetDict() {
		
		ArrayList<String> check = new ArrayList<String>();
		check.add("sdfs1");
		assertEquals("Cannot get list", check, Data.getDict());	}

}
