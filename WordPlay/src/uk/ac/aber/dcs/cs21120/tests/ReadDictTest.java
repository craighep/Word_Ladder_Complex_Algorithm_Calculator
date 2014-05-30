/**
 * 
 */
package uk.ac.aber.dcs.cs21120.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * Test for checking the readWords method in this class reads in the dictionary file correctly
 * by reading the file, then getting the first element and using an assertEquals test in order
 * to see if it gets correct value as specified within the text file.
 * @author Craig
 *
 */
public class ReadDictTest {	
	/**
	 * Test method for {@link uk.ac.aber.dcs.cs21120.ReadDict#readWords}.
	 * @throws IOException 
	 */
	@Test
	public void testReadWords() throws IOException {
		FileReader fin = new FileReader( "dictionary.txt" );
		BufferedReader bin = new BufferedReader( fin );
		String oneLine;
		List<String> wordsRead = new ArrayList<String>( );

		while( ( oneLine = bin.readLine( ) ) != null )
			wordsRead.add( oneLine );
		assertEquals("file unsuccsesfully read", "aa", wordsRead.get(0));
	}

	
}
