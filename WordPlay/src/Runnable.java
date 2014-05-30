import java.io.IOException;

import uk.ac.aber.dcs.cs21120.data.AltStart;
import uk.ac.aber.dcs.cs21120.data.ReadDict;


/**
 *This class is responsible for the running of the program and setting up
 * the dictionary by initiating the ReadDict() class. It throws an IOException 
 * in case of the file for the dictionary not being found.
 * 
 * @author Craig
 */
public class Runnable {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome To WordPlay! Please Select An Option.");
		ReadDict read = new ReadDict(); // create new instance of dictionary class
		read.read();                    // read the text file and place in a list
		AltStart start = new AltStart(); 
		start.start();                  //begin the main program
	}

}