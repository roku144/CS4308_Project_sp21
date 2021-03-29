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
		ParseLinkedList PLL = new ParseLinkedList(Parse.readFile());
		PLL.beginParse();
		
		//Clearing the information currently stored in the statements.txt file
		try {
			PrintWriter writer = new PrintWriter("statements.txt");
			writer.print("");
			writer.close();
		}
		catch (FileNotFoundException e1) {
			System.out.println("Program attempted to clear \"statements.txt\", but no such file exists.");
		}
		
		//Creating output file object for the statemnts.txt file
		File output = new File("statements.txt");
	    try {
			if (!output.exists()) {
				output.createNewFile();
			}
			
			//Creating the ability to write to the output file without 
			//overwriting the content currently stored in the file
			PrintWriter PW = new PrintWriter(new FileWriter(output, true));
			
			//Storage of the intermediate information until the full statement has been read
			String str = "";
			//Output the List created by the parser to the statements.txt file
	    	for(int i=0; i < PLL.parsedTree.size(); i++){
	    		if (PLL.parsedTree.get(i).equals("9000")) {
	    			str += "\n";
	    			PW.append(str);
	    			str = "";
	    		}
	    		else {
	    			str += " " + PLL.parsedTree.get(i);
	    		}
	    	}
	    	PW.close();
	    }catch(IOException e){
	    	System.out.println("Error opening output file.");
	    }
	}
}
