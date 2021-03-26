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

import java.util.*;
import java.io.*;


public class Parse {
	static LinkedList ll = new LinkedList();
	static String FileName = "temp.txt";
	public static void main(String args[]) {
	
	readFile();

	ll.display();
	
	
	}//end main
	public static void readFile() {
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
	}//end readFile


	
	//21-function, 22-begin, 23-endfun, 24-constants, 25-variables, 26-define, 27-set, 29-display, 30-input, 37-if, 39-endif, 40-elseif, 41-else
	
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