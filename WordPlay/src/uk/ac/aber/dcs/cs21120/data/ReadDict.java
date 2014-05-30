package uk.ac.aber.dcs.cs21120.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads in the dictionary text file and places it into an organised array list 
 * ready for use in the Generation and Discovery games. Uses a buffered reader to read
 * each line of the text file and adds the word to the array list.
 * @author Craig
 */
public class ReadDict {
	/**
	 *
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void read() throws IOException, FileNotFoundException{
		FileReader fin = new FileReader( "dictionary.txt" ); //Read file into program
		BufferedReader bin = new BufferedReader( fin );
		List<String> words = readWords( bin ); //read words from file
		Data data = new Data();
		data.setDict(words);
	}

	//Reads in each line from the dictionary, and adds each to the array list wordsRead
	/**
	 *
	 * @param in
	 * @return wordsRead
	 * @throws IOException
	 */
	public static List<String> readWords( BufferedReader in ) throws IOException
	{
		String oneLine;
		List<String> wordsRead = new ArrayList<String>( );

		while( ( oneLine = in.readLine( ) ) != null )
			wordsRead.add( oneLine ); //add each word to list for computing later on

		return wordsRead;
	}    
}
