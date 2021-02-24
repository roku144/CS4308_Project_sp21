/* 		Author:Joel Smith
 * 		Group: Parker Browne, Gautam Dubhashi, Joel Smith
 * 		Class: CS4308 - Concepts of Programming Language(Sp 2021)
 * 		
 * 		Description:
 * 	
 *		Scan is the entry point of the scanner which will call all the subsequent parts of the scanner.
 * 		
 * 		The functions of this Scan class are as follows:
 * 		-Main() - provides the reading of the given file into its individual lines
 * 		-SearchLine() - takes the given string(line) and separates out the individual substrings that are separated by a space (" ")
 * 		-Read() - Reads the string for its basic characters and assigns a string category(Not final tokens for the given lexeme, but a 
 * 					general category such as "Number", "Symbol", "AlphaNumeric", or "Unknown")
 * 		-Check() - will analyze the individual characters to ensure that are defined for the standard dictionary of English characters
 * 					(Digits {0-9}, Letters{a-z, A-Z}, and Keyboard symbols e.g. ',' , '.', etc.)
 * 		-Token() - Assigns a token to the given lexeme
 */

import java.util.Scanner;

import java.io.*;
public class Scan {
	private static final String[] KEYWORDS = {"if","elseif", "then", "else","endif","function", "function", "begin", "endfun", "define", "set", "variables", "constants", "type", "integer", "double", "boolean", "character", "true", "false", "display", "input"};
	private static final String[][] SYMBOLS = {{"(","LPARENTHS"}, {")", "RPARENTHS"}, {"<","LASNGB"},{">","RANGB"}, {"\"", "QUOTES"}, {"\'", "SINGQUOTE"}, {".", "DOT_OP"}, {",", "COMMA"},{"+", "ADD_OP"}, 
					{"-","MINUS_OP"}, {"*","MULTI_OP"}, {"/","DIVIDE_OP"}, {"=", "EQS_OP"}, {"==","EQUIV_OP"},  {"<=","LESSEQ_OP"}, {">=","GREATEREQ_OP"}, {"<=","LESSEQ_OP"}, {"!","NOT_OP"}, {"!=","NOTEQ_OP"}};
	private static String[] identifiers = new String[25]; //identifiers structure of [identifier_name][identifier_type]
	private static int filled = 0;
	public static void main (String[] args) {
		String FileName = "";
		Scanner UserInput = new Scanner(System.in);
		
		/*
		 * Main function loop
		 * 
		 * Provides the user with a command line to input the path and file they are wanting to scan.
		 * Upon receiving a valid file, we will then analyze the file line by line until we reach the end of the file provided.*/
		do {
			System.out.println("Enter the path and name of the file you wish to scan: ");
			FileName = UserInput.nextLine();
			int LineNumber = 0;
			
			try (BufferedReader BR = new BufferedReader(new FileReader(FileName))){
				String Line;
				while ((Line = BR.readLine())!= null) {
					LineNumber++;
					//Removing whitespace between paragraphs
					if(Line.equals("")) {
						continue;
					}
					
					//Removing single line comments
					else if (Line.contains("//")) {
						Line = Line.replace(Line.substring(Line.indexOf("//")),"").trim();
						if(Line.equals("") ||Line.equals(" \n")) {
							continue;
						}						
					}
					
					//Removing block comments 
					else if (Line.contains("/*")) {
						Line = Line.replace(Line.substring(Line.indexOf("/*"),((Line.indexOf("\n")+1)-(Line.indexOf("/*")-2))),"").trim();
						if (!Line.equals("")) {
							;
						}
						//Looping for the end of the multi-lined comment
						CommentBlock:
							while (!(Line = BR.readLine()).contains("*/") && Line != null) {
								LineNumber++;
								continue CommentBlock;
							}
						
						//Handling of if there is no */ to end the multi-line comment
						if (Line == null) {
							break;
						}
						else {
							Line = Line.replace(Line.substring(0,(Line.indexOf("*/")+2)), "").trim();
							if (Line.equals("")||Line.equals(" \n")) {
								LineNumber++;
								continue;
							}
						}
					}
					
					//Ensuring the symbols are scanned separate from the rest of the phrase
					Line = SymbolBreak(Line).trim();
					//Searching of the individual words on the line read
					SearchLine(Line, LineNumber);
				}
				BR.close();
				break;
			}
			catch (IOException e) {
				System.out.println("Incorrect or missing file name");
				FileName = "";
			}
		}while (!FileName.equals(""));
		UserInput.close();
	}	
	
	//SearchLine takes a string from its parameter in order to split the string into its substrings that are separated by the " " character
	public static void SearchLine(String Line,int Line_Number) {
		String Lexeme_Type, Token;
		
		//Removal of additional spaces in the given line
		if (Line.contains("  ")){
				Line = Line.replaceAll("  ", " ");
		}
		
		//Split line into individuals word separated by a SPACE 
		String[] Lexemes = Line.split(" ");
		//Loop for the removal of spaces within the individual words;
		for (int i = 0; i <Lexemes.length; i++) {
			Lexemes[i] = Lexemes[i].replaceAll(" ", "");
			Lexeme_Type = Read(Lexemes[i]);
			//Providing error message to user with the line number and column number of the error
			if (Lexeme_Type.equals("Unknown")) {
				System.out.printf("Unknown character on row %d column %d\n", Line_Number, Line.indexOf(Lexemes[i]));
				continue;
			}
			else {
				//Call Token() to retrieve the correct token for the current Lexeme
				Token = Token(Lexemes[i],Lexeme_Type);
				//Output the Lexeme and its Token
				System.out.printf("Lexeme %s has Token %s\n", Lexemes[i],Token);
			}
		}
			
	}
	
	//Reading the words individually to properly decide the category the Lexeme belongs to
	public static String Read(String str) {
		//Function variables
		String strType = "Unknown";
		char[] Check = str.toCharArray();
		int SymbolCount = 0;
		
		for (int i = 0; i < Check.length; i++){
			//Loop variables
			String tempType, Alpha = "AlphaNumeric", Num = "Number", Sym = "Symbol", Uk = "Unknown";
			tempType = Check(Check[i]); //Function call for checking the individual characters in the given string
			
			//When the String "Number" is returned from Check() 
			if (tempType.equals(Num)) {
				if(strType.equals(Num)) {
					continue;
				}
				else if (strType.equals(Alpha)) {
					strType = Uk;
					break;
				}
				else if(strType.equals(Sym)) {
					strType = Uk;
					break;
				}
				else {
					strType = Num;
				}
			}
			
			//When the String "AlphaNumeric" is returned by Check() 
			else if (tempType.equals(Alpha)) {
				if (strType.equals(Alpha)) {
					continue;
				}
				else if(strType.equals(Num)) {
					continue;
				}
				else if(strType.equals(Sym)) {
					strType = Uk;
					break;
				}
				else {
					strType = Alpha;
				}
			}
			
			//When the String "Symbol" is returned from the function Check()
			else if (tempType.equals(Sym)) {
				if (strType.equals(Alpha) && (Check[i] == '_' || Check[i] == '.')) {
					continue;
				}
				else if (strType.equals(Num)) {
					if (Check[i] == '.' && SymbolCount == 0) {
						SymbolCount = 1;
						continue;
					}else {
						strType = Uk;
					}
					
				}
				else if (strType.equals(Sym)){
					continue;
				}
				else {
					strType = Sym;
				}
			}
			
			//If the Function Check() returns the String "Unknown"
			else {
				strType = Uk;
				break;
			}
		}
		
		//Return the found strType to the called function
		return strType;
	}
	
	//Evaluation of each character for better devision between Alpha-numeric combinations and numbers/symbols
	public static String Check(char ch) {
		int chi = (int) ch;
		if (chi >= 33 || chi <= 126) {
			//ASCII values that define the digits 0-9
			if (chi >= 48 && chi <= 57) {
				return "Number";
			}
			
			//ASCII values that define a-z and A-Z
			else if ((chi >= 65 && chi <= 90) || (chi >= 97 && chi <= 122)){
				return "AlphaNumeric";
			}
			//ASCII values for all symbols
			else
			{
				return "Symbol";
			}
		}
		
		/* Unknown are characters that lie outside of the range for the ASCII table of 
		 * character values for standard English letters, numbers, and regular symbols*/
		else {
			return "Unknown";
		}
	}
	
	//Putting space between common symbols and the rest of the files to separate one lexeme from another
	public static String SymbolBreak(String str) {
		if (str.contains("(")) {
			str = str.replace("(", " ( ");
		}
		if (str.contains(")")) {
			str = str.replace(")", " ) ");
		}
		if (str.contains("\"")) {
			str = str.replace("\"", " \" ");
		}
		if (str.contains("\'")) {
			str = str.replace("\'", " \' ");
		}
		if (str.contains(".")) {
			str = str.replace(".", " . ");
		}
		if (str.contains(",")) {
			str = str.replace(",", " , ");
		}
		return str;
	}
	
	//Return the correct token to the given lexeme
	public static String Token(String Lexeme, String Lexeme_Type) {
		String Token = "Unknown"; 
		if (Lexeme_Type.equals("Number")) {
			boolean isNumber = false;
			try {
				//test whether of not the current number Lexeme is a Integer --ture
				if (Double.parseDouble(Lexeme)%1 == 0) {
					//Set current Lexeme's Token to INT_LIT
					Token = "INT_LIT";
					isNumber = true;
				}
				//Found Double -- false
				else {
					//Set current Lexeme's Token to DOUB_LIT
					Token = "DOUB_LIT";
					isNumber = true;
				}
				
			}finally{
				if (!isNumber) {
					Token = "Unknown";
				}
			}
			
		}
		else if (Lexeme_Type.equals("AlphaNumeric")) {
			for (int i = 0;i<KEYWORDS.length;i++) {
				if (KEYWORDS[i].equals(Lexeme)) {
					//Set current Lexeme's Token to the Token of the found keyword
					Token = KEYWORDS[i].toUpperCase();
					break;
				}
			}
			if (Token.equals("Unknown")){
				int i = 0;
				//Check current AlphaNumeric against identifiers array
				while(i < filled) {
					if (identifiers[i].equals(Lexeme)) {
						//Set current Lexeme's Token to IDENTIFIER
						Token = "IDENTIFIER";
						break;
					}
					i++;
				}
				if (Token.equals("Unknown")) {
					//Adds current Lexeme to the list of identifiers
					identifiers[i] = Lexeme;
					Token = "IDENTIFIER";
				}
			}
		}
		
		//Compares the current Lexeme with the defined symbols stored in the array SYMBOLS
		else if (Lexeme_Type.equals("Symbol")) {
			for (int i = 0;i < SYMBOLS.length; i++) {
				if (SYMBOLS[i][0].equals(Lexeme)) {
					//Sets the current Lexeme's Token to be the token of the symbol stored in SYMBOLS
					Token = SYMBOLS[i][1];
				}
			}
		}
		//Return Token to called function
		return Token;
	}
}

