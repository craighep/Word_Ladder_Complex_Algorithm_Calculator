package uk.ac.aber.dcs.cs21120.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *Class responsible for asking the user's option of which ladder game they would
 * like to play, and continuously asks until user inputs the correct String
 * required.
 * 
 * @author Craig
 */
public class AltStart {
	/**
	 *Keeps looping until user types correct string matching either a game or
	 * to exit the program. 
	 * @throws IOException
	 */
	public void start() throws IOException{
		String option = "";
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );

		while (!option.equals("1") || !option.equals("2")){	//repeat this until valid input
			System.out.println("Type for: 1- Discovery, 2-Generation     ('x' to exit)");
			option = in.readLine();

			if (option.equals("1")){  //if user chooses option 1, run the discovery section of program
				Discovery wl = new Discovery();
				wl.run();
			}
			if (option.equals("2")){ //if users chooses 2, run generation section of program
				Generation sl = new Generation();
				sl.run();
			}
			if(option.equals("x") || option.equals("X")){  //exit when user enters 'x'
				System.exit(0);
			}
		}
	}


}
