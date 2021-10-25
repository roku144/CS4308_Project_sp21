# CS4308_Project_sp21

# CS4308_SCL_Interpreter
## Interpreter for the SCL language, written in Java. 

### The project is broken down into 3 parts: the Scanner, the Parser, and the Interpreter. 

#### Main - 100% Complete
* Class that acts as the intemediary between the various classes of this project.
* Passes needed ifnromation from one component of the language processor to the next
* Runs the final Output file as a java command line execution.

#### Scanner - 100% Complete
* The Scanner reads the source file, line by line, and converts each useful word into a Token object.
* A Token holds the original word and a numerical code that represents the word's function within the language.
* The Scanner returns a "tokenized" line to the Parser.

####Suggested changes:
* Improve reading of characters in Check()
* Removal of user input from the scanner and support the input of file/path by means of paramater passing

#### Parser - 100% Complete
* Reads Through passed list of tokens and lexemes to determine proper grammar format.
* Formats the properly parsed grammatical statements into a list of arrays representing the individual lines of target output formatting
* Returns to the Main.java class

#### Interpreter - 100% Complete
* Takes the giving list of arrays from the Main.java class and converts the grammatical rules to java main class.
* The interpreter works line by line, as represented by an array stored within the list passed to the interpreter, to translate from grammar to java syntax.
