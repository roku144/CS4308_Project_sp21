/************************************************************
*Parker Browne
*CPL project
*deliverible 2
*
*
*
*
*
*************************************************************/

import java.io.*;


public class Parse {
	//linked list for the tokens and lexemes 
	//not sure if declaring it as static will cause issues but it runs for now.
	static LinkedList ll = new LinkedList();

	//file temp.txt is used as an intermedate from the scanner to the parser
	static String FileName = "temp.txt";

	
	//reads from temp.txt line by line, splits the tokens and lexemes into seprate varibles 
	//then calls ParseCase, which takes those variables 
	public static Object readFile() {
		try {
			BufferedReader BR = new BufferedReader(new FileReader(FileName));
			String fileLine;
			int Tok;
			String Lex;

			while((fileLine = BR.readLine())!= null) {
				String[] arrOfFile = fileLine.split(" ");
				Tok = Integer.parseInt(arrOfFile[0]);
				Lex = arrOfFile[1];
				ParseCase(Tok, Lex);
			}//end while

			BR.close();

		}//end try
		catch (IOException error) {
			System.out.print("An error happened during file reading");
			error.printStackTrace();
		}//end catch
		
		//Return LinkedList object to file
		return ll;
	}//end readFile

	//this method checks to see if a particular Token needs to have space added behind it
	//then adds that token and lexeme to the linked lists
	public static void ParseCase(int Tokens, String Lexemes) {
		
		switch(Tokens) {
			case 21: //function
				ll.add(Tokens, Lexemes);
				break;

			case 22: //begin
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 23: //endfun
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 24: //constants 
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 25: //variables
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 26: //define
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 27: //set
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 29: //display
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 30: //input
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 37: //if
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 39: //endif
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 40: //elseif
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			case 41: //else
				ll.add(9000, "blank");
				ll.add(Tokens, Lexemes);
				break;

			default:
				ll.add(Tokens, Lexemes);
		}//end switch
		
	}//end ParseCase

}//end class Parse
