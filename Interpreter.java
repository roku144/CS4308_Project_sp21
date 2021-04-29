/************************************************************
* 	Author: Parker Browne
*	Group: Parker Browne, Gautam Dubhashi, Joel Smith
* 	Class: CS4308 - Concepts of Programming Language(Sp 2021)
*
*************************************************************/

import java.io.*;

public class Interpreter {

    //file with the tokens
    static String fileName = "statments.txt";
    //target file
    static File output = new File("projectOut.java");

    /*{{"function", "21"}, {"begin", "22"}, {"endfun", "23"}, {"constants", "24"}, {"variables", "25"},
    *  {"define", "26"}, {"set", "27"},  {"type", "28"}, {"display", "29"}, {"input", "30"}, {"integer", "31"},
    *  {"double", "32"}, {"boolean", "33"}, {"character", "34"}, {"true", "35"}, {"false", "36"}, {"if", "37"}, 
    *  {"then", "38"}, {"endif", "39"}, {"elseif", "40"}, {"else", "41"}};

    * {{"+", "1"}, {"-","2"}, {"*","3"}, {"/","4"}, {"=", "5"}, {"==","6"},  {"<","7"}, {">","8"}, {">=","9"},
    *  {"<=","10"}, {"!","11"}, {"!=","12"}, {"(","13"}, {")", "14"}, {"\"", "17"}, {"\'", "18"}, {".", "19"}, 
    *  {",", "20"}};
    */  

    private static final String[][] KEYWORDS = {{"public class projectOut {", "21"}, {"public static void main(String[] args) {", "22"},
                                                {"}//end class/main", "23"}, {"constants", "24"}, {"variables", "25"}, {"define", "26"}, 
                                                {"set", "27"},  {"type", "28"}, {"display", "29"}, {"input", "30"}, {"int", "31"},
                                                {"double", "32"}, {"boolean", "33"}, {"character", "34"}, {"true", "35"}, {"false", "36"},
                                                {"if", "37"}, {"then", "38"}, {"}//end if/else", "39"}, {"elseif", "40"}, {"else {", "41"}};

	private static final String[][] SYMBOLS = {{"+", "1"}, {"-","2"}, {"*","3"}, {"/","4"}, {"=", "5"}, {"==","6"},  {"<","7"}, {">","8"},
                                               {">=","9"}, {"<=","10"}, {"!","11"}, {"!=","12"}, {"(","13"}, {")", "14"}, {"\"", "17"},
                                               {"\'", "18"}, {".", "19"}, {",", "20"}};
    // readStmt() is the entrance for the interpreter, it looks at the SCL tokens stored in statements.txt
    // splits each line at a space, stores the tokens from that line in an array and calls writeFile(arrOfFile)
    // passing the array of tokens
    public static void readStmt() {
        try{
            BufferedReader BR = new BufferedReader(new FileReader("statements.txt"));
            String fileLine;
            FileWriter out = new FileWriter(output);
            //clearing the target file to write translated SCL code into java
            out.write("");
            out.close();

            while((fileLine = BR.readLine()) != null) {
                String[] arrOfFile = fileLine.split(" ");
                writeFile(arrOfFile);
            }//end while

            BR.close();
        }//end try
        catch(IOException error) {
            System.out.print("An error occored in translation file reading");
            error.printStackTrace();
        }//end catch

    }//end readStmt


    //tokens are stored in an array in the order read from statements.txt
    //useing a large switch statement the tokens are matched with their java equvalants
    //refering back to the KEYWORDS[][] and SYMBOLS[][] as much as possible 
    public static void writeFile(String[] arrToken) {
        String token;
        String codeOut = "";
        
        try {
            if(!output.exists()) {
                output.createNewFile();
                System.out.println("new file made");
            }//end if
            //if the boolian true is passed it appends the file, this is why we clear the file in readStmt()
            //if the boolian false is passed the output file is only filled with the last command
            FileWriter out = new FileWriter(output, true);

            //iterate through the array of tokens
            for(int i = 0; i < arrToken.length; i++) {
                token = arrToken[i];

                //all tokens are strings
                switch(token) {
                    case " ": //blank, just ignore
                        break;

                    case "": //also blank
                        break;

                    case "21": //SCL function   java class and main
                        //          java class 
                        codeOut = KEYWORDS[0][0];
                        out.append(codeOut + "\n");
                        //          java main
                        codeOut = KEYWORDS[1][0];
                        out.append(codeOut+"\n");
                        break;

                    case "23": //SCL endfun    java closing brackets for main and class
                        //          closing bracket
                        codeOut = KEYWORDS[2][0];
                        // twice to close class and main
                        out.append(codeOut+"\n");
                        out.append(codeOut+"\n");
                        break;

                    case "26": //SCL define  used to define a varible as what ever type is given
                        // the length of the array tells us if the varable gets defined now or is defined as null
                        // the varable name is also in the array and used appropreatly 

                        // token at i+3 is the token for the given type, we check if that token is equal to the token for
                        // SCL integer, can be expanded to check for other data types

                        // if the length of the array is 5 we know the varable is assigned null
                        //                          given type          token for integer
                        if(arrToken.length == 5 && arrToken[i+3].equals(KEYWORDS[10][1])) {
                            //             int                variable name
                            codeOut = KEYWORDS[10][0] + " " + arrToken[i+1] + ";";
                            out.append(codeOut+"\n");
                            break;

                        // if the length of the array is 7 we know the varable gets assigned a value 
                        //                                  given type          token for integer
                        } else if(arrToken.length == 7 && arrToken[i+3].equals(KEYWORDS[10][1])) {
                            //             int               variable name              =               value
                            codeOut = KEYWORDS[10][0] + " " + arrToken[i+1] + " " + SYMBOLS[4][0] + arrToken[i+5] + ";";
                            out.append(codeOut + "\n");
                            break;
                        }//end if else
                        break;
                    
                    case "27": // SCL set assigning a value to an already initilized varable
                        //if the length is 5 we know its assigned to a single value
                        if(arrToken.length == 5) {
                            //        varable name              =                   value
                            codeOut = arrToken[i+1] + " " + SYMBOLS[4][0] + " " + arrToken[i+3] +";";
                            out.append(codeOut+"\n");
                            break;
                        
                        //if the length of the array is 7 we know the varable gets assigned to a combanation of varables or values
                        //the if statemt below determes if the varables or values are combined with the + opporator
                        //                                                       token for +
                        } else if(arrToken.length == 7 && arrToken[i+4].equals(SYMBOLS[0][1])) {
                            //        varable name              =                 var or value              +                 var or value
                            codeOut = arrToken[i+1] + " " + SYMBOLS[4][0] + " " + arrToken[i+3] + " " + SYMBOLS[0][0] + " " + arrToken[i+5] + ";";
                            out.append(codeOut+"\n");
                            break;
                            //out.println();
                        //if the length of the array is 7 we know the varable gets assigned to a combanation of varables or values
                        //the if statemt below determes if the varables or values are combined with the - opporator
                        //                                                       token for -
                        } else if(arrToken.length == 7 && arrToken[i+4].equals(SYMBOLS[1][1])) {
                            //        varable name              =                 var or value              -                 var or value
                            codeOut = arrToken[i+1] + " " + SYMBOLS[4][0] + " " + arrToken[i+3] + " " + SYMBOLS[1][0] + " " + arrToken[i+5] + ";";
                            out.append(codeOut+"\n");
                            break;
                            //                                                  token for *
                        } else if(arrToken.length == 7 && arrToken[i+4].equals(SYMBOLS[2][1])) {
                            //        varable name              =                 var or value              -                 var or value
                            codeOut = arrToken[i+1] + " " + SYMBOLS[4][0] + " " + arrToken[i+3] + " " + SYMBOLS[2][0] + " " + arrToken[i+5] + ";";
                            out.append(codeOut+"\n");
                            break;
                        }
                        break;
                    
                    case "37": //SCL if  starts an if statement with the given arguments
                    // currently only works with >= and <
                    // just copy past and change the SYMBOLS[][] for an exaustave lists of possible if statement 
                        //if the token at i+3 is the token for >=
                        if(arrToken[i+3].equals(SYMBOLS[8][1])) {
                        //                  if                      (             varable               >=                  varable             )
                            codeOut = KEYWORDS[16][0] + " " + SYMBOLS[12][0] + arrToken[i+2] + " " + SYMBOLS[8][0] + " " + arrToken[i+4] + SYMBOLS[13][0] + "{";
                            out.append(codeOut+"\n");
                            break;
                        //if the token at i+3 is the token for <
                        } else if(arrToken[i+3].equals(SYMBOLS[6][1])) {
                            //                  if                      (             varable               <                  varable             )
                            codeOut = KEYWORDS[16][0] + " " + SYMBOLS[12][0] + arrToken[i+2] + " " + SYMBOLS[6][0] + " " + arrToken[i+4] + SYMBOLS[13][0] + "{";
                            out.append(codeOut+"\n");
                            break;
                        }//end else if

                    case "39": //SCL end if the closing brakets for the if statement and else statement
                        //              }
                        codeOut = KEYWORDS[18][0];
                        out.append(codeOut + "\n");
                        break;

                    case "41": //SCL else works like a normal else statement 
                        //          else {
                        codeOut = KEYWORDS[20][0];
                        out.append(codeOut +"\n");
                        break;
                    
                    case "29": //SCL display  using java System.out.println();
                            codeOut = "System.out.println(" + arrToken[i+1] + ");";
                            out.append(codeOut + "\n");
                            break;
                    default:

                        break;
                }//end switch
        }//end for
            
            out.close();
        }//end try
        catch(IOException writeError) {
            System.out.print("An error occored in translation file Writnig");
            writeError.printStackTrace();
        }//end catch

    }//end writeFile

    public static void main(String[] args) {
        readStmt();
    }//end main
}//end class

