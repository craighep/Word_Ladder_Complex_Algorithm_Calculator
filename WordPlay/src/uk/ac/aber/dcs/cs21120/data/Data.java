package uk.ac.aber.dcs.cs21120.data;

/**
 *Stores the data for list containing the dictionary of words used in the
 * ladders, using the get and set methods. Set method used in the ReadDict() 
 * class.
 * @author Craig
 */
public class Data {


	/**
	 *List of strings containing the dictionary used in the program
	 */
	public static java.util.List<String> dictionary;

	/**
	 *Takes in a list of strings (words), and sets the list in this class to
	 * that value.
	 * @param words
	 */
	public void setDict(java.util.List<String> words){
		dictionary = words;
	}
	/**
	 *When run from either ladder classes, returns a list of strings which are
	 * the words from the dictionary file.
	 * @return dictionary
	 */
	public java.util.List<String> getDict(){
		return dictionary;

	}

}

