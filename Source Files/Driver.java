/*
 * 		Author:Joel Smith
 * 		Group: Parker Browne, Gautam Dubhashi, Joel Smith
 * 		Class: CS4308 - Concepts of Programming Language(Sp 2021)
 * 		
 * 		Description:
 * 		Driver.java is the entry point for the entire language processor.
 * 		
 * 		The Main function gains user input to open a FileReader object which is then passed to the Scan class for the scanner to run.
 * 		Once the Scanner has run, the Driver will then call the Parser via ...
 */
import java.io.*;
import java.util.Scanner;
public class Driver {
	public static void main(String[] args) {
		Scanner UserInput = new Scanner(System.in);
		String FileName ="";
		do {
			//Gaining user input for file name
			System.out.println("Enter the path and name of the file you wish to scan: ");
			FileName = UserInput.nextLine();
			
			try {
				//Calling the scanner and passing the FileReader object
				Scan.readFile(new FileReader(FileName));
				FileName = "";
			}
			catch(IOException e) {
				System.out.println("Incorrect or missing file name");
			}
		}while (!FileName.equals(""));
		UserInput.close();
		
		//Calling the Parser
		Parse.readFile();
	}
}
