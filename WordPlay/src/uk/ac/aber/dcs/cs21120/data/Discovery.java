package uk.ac.aber.dcs.cs21120.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 *This class is responsible for creating the word ladders between two given
 * words by the user. Uses the dictionary from the data class, and then 
 * organises this to create a hash map of words, sorting them into nearby
 * matching words storing them as a list with the key as a value(word).
 * @author Craig
 */
public class Discovery
{
	/**
	 *Create an empty map of Strings and lists of strings
	 */
	public Map<String,List<String>> nearbyWords; 

	/**
	 *The main method in this class in which it prepares the system for calculating the routes of two words. This method 
	 *also stops the user having to wait for the program to re-compute the near by words every time a route is calculated.
	 * @throws IOException
	 */
	public void run() throws IOException
	{
		double start, end;
		double time;

		Data data = new Data();
		List<String> words = data.getDict(); // get list of dictionary words from data class
		System.out.println( "Program Recieved " + words.size( ) + " Words..");
		System.out.println("LOADING...");
		System.out.println("\n" ); 
		start = System.currentTimeMillis( ); // create a timer to see how long getting the nearby words takes
		nearbyWords = computeNearbyWords( words ); //get near by words (words that link together)
		end = System.currentTimeMillis( );
		time = (end-start)/1000;	//Calculate time taken to get nearby words linked

		System.out.println( "Elapsed time to compute: " + time + " seconds");
		go(words); /*run the go method taking in the dictionary list, avoiding having to
 					calculate the nearby words again after each input */

	}
	/**
	 *Method for checking user input, and selecting what words must be bridged between. Also 
	 *calls the methods that links the words by sending the words chosen along with the map 
	 *of nearby words.
	 * @param words
	 * @throws IOException
	 */
	public void go(List<String> words) throws IOException{   	
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) ); //create buffered reader
		while(true){
			System.out.println("To exit to menu, type 'x' at any time.");
			System.out.println("\n" ); 
			System.out.println( "Enter the first word: " );
			String w1 = in.readLine( ); //read in first word
			if(w1.equals("x") || w1.equals("X")){ //if the input is 'x', then return to the menu to select a game
				clrScr('\n'); //calls the clear screen method
				AltStart start = new AltStart(); 
				start.start();   
			}
			if (!words.contains(w1)){clrScr('\n'); //checks if word is in the dictionary
			System.out.println("Word '"+w1+"' not in dictionary!");} //clear screen and inform user of error
			else{
				System.out.println( "Enter the second word: " ); 
				String w2 = in.readLine( ); //read in the second word
				if(w2.equals("x") || w1.equals("X")){ //if input is 'x' go to menu
					clrScr('\n'); //clear screen
					AltStart start = new AltStart();
					start.start();   
				}if (w1.length() != w2.length()){ //check if words are same length
					clrScr('\n');
					System.out.println("Words not same length!"); //report error and start again
				}
				else{
					if (!words.contains(w2)){clrScr('\n'); //check if word 2 is in dictionary
					System.out.println("Word '"+w2+"' not in dictionary!");}
					else{

						List<String> path = findPath( nearbyWords, w1, w2 ); //run the find path method to calculate ladder
						clrScr('\n'); //clear screen after running this
						if(path.size() ==0){ //if path equals 0, then no path could be found and tell user
							System.out.println("Path could not be found");
						}
						else {
						System.out.println( "PathSize was " +path.size( ) + "..." ); //print path size otherwise
						for( String word : path ) //get each String from the array,
							System.out.print( " " + word ); //and print each individual String
						System.out.println("\n" );    
					}}}}}
	}
	/**
	 *Updates each list of words by taking in the dictionary list, and a key (length of words to be added for example)
	 *and the word which is to be sorted into the appropriate list. Creates a new list if the length of word does not 
	 *have a section yet.
	 * @param m
	 * @param key
	 * @param value
	 */
	private static <KeyType> void update( Map<KeyType,List<String>> m, KeyType key, String value )
	{
		List<String> lst = m.get( key ); //gets the strings attached with the section of the map associated with key 
		if( lst == null ) // if the list is empty, create a new one 
		{
			lst = new ArrayList<String>( );
			m.put( key, lst ); //create a new  section in the map, assigning it the key and what Strings appear in it
		}

		lst.add( value ); 
	}



	/**
	 *Creates a map in which the keys are words and values are Lists of words
	 *that differ in only one character from the corresponding key.
	 *
	 * @param words
	 */
	public static Map<String,List<String>> computeNearbyWords( List<String> words )
	{
		Map<String,List<String>> nearbyWords = new HashMap<String,List<String>>( ); //create new String HashMap of nearby words
		Map<Integer,List<String>> wordsLenghth = new HashMap<Integer,List<String>>( ); //create a new HashMap of words sorted by length

		// Group the words by their length
		for( String w : words ){
			update( wordsLenghth, w.length( ), w );} //run the update method to add the group words by length to lists
		// and then back to the wordsLength map
		// Work on each group separately
		for( Map.Entry<Integer,List<String>> entry : wordsLenghth.entrySet( ) )
		{    
			List<String> groupsWords = entry.getValue( );
			int groupNum = entry.getKey( );

			// Work on each position in each group
			for( int i = 0; i < groupNum; i++ )
			{
				// Remove one character in certain position, calculating represent.
				// Words with same representative are nearby
				Map<String,List<String>> repToWord = new HashMap<String,List<String>>( );//create new HashMap of
				//Strings storing words 
				//differing by one char

				for( String str : groupsWords )
				{		// for each string in a group of words, change one char 
					String rep = str.substring( 0, i ) + str.substring( i + 1 );
					update( repToWord, rep, str ); //call update method to update the repToWord map and sort 
					//into key sections
				}

				// check for map values with more than one string
				for( List<String> wordCopy : repToWord.values( ) ) //for each section of the map
					if( wordCopy.size( ) >= 2 ) // if contains more than 1 word in section
						for( String s1 : wordCopy ) //get each string 
							for( String s2 : wordCopy )
								if( s1 != s2 ) //compare to check strings are not the same
									update( nearbyWords, s1, s2 ); //update the nearbyWords map as an organised set of 
				// keys that contain words that link together
			}
		}

		return nearbyWords; //return the near by words map
	}

	
	/**
	 *Runs the shortest path calculation from the nearbyWords map, returning a List
	 *that contains the sequence of words changes to get from first to second.
	 * @param nearbyWords
	 * @param first
	 * @param second
	 * 
	 */
	public static List<String> findPath( Map<String,List<String>> nearbyWords, String first, String second )            
	{
		Map<String,String> previousWord = new HashMap<String,String>( ); //create HashMap of strings storing the previous word
		Queue<String> q = new LinkedList<String>( ); //create an empty queue of words within a linked list

		q.add( first ); //add the word the user wishes to start with
		while( !q.isEmpty( ) ) //run whilst the queue is not empty
		{
			String current = q.element( ); q.remove( );  //create a new string and assign it as the element in the Queue, then 
			//remove the item from the queue
			List<String> nrb = nearbyWords.get( current ); //create list of strings, and assign values as the nearbyWords certain key value

			if( nrb != null )	//if the list from the key chosen is not empty
				for( String nrbWord : nrb ) // get each string from the list
					if( previousWord.get( nrbWord ) == null ) // and if the previous word does not contain the word from the nearby word
					{
						previousWord.put( nrbWord, current ); // add the new word with an assigned key as the nearby word
						q.add( nrbWord );	//add the nearby word to the queue and go through loop again
					}                                                
		}

		previousWord.put( first, null ); //overwrite the first key word to null
		return getPathFromPrevious( previousWord, first, second ); //return the path as a result of placing all the calculated route together
	}
	/**
	 *After the shortest path calculation has run, computes the List that
	 *contains the sequence of word changes to get from first to second.
	 * @param prev
	 * @param first
	 * @param second
	 * @return result
	 */
	public static List<String> getPathFromPrevious( Map<String,String> prev,
			String first, String second )
			{
		LinkedList<String> result = new LinkedList<String>( ); //create the path result linked list of strings

		if( prev.get( second ) != null ){ // check if the chain is longer than two 
			for( String str = second; str != null; str = prev.get( str ) ){  //run through until all the path is added on
				result.addFirst( str );}
		}
		return result; //return the path result for output
			}


	/**
	 * Clears the screen when calling. for example after output, moves the command line up 25 times to hide the last output, 
	 * making it more organised and easy to use for the users.
	 * @param c
	 */
	public void clrScr(char c){
		int length = 25;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}
}
