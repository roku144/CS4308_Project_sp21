# CS4308_Project_sp21

# CS4308_SCL_Interpreter
## A light interpreter for the SCL language, written in Python. 

### The project is broken down into 3 parts: the Scanner, the Parser, and the Interpreter. 

#### Scanner - This is complete. 
* The Scanner reads the source file, line by line, and converts each useful word into a Token object.
* A Token holds the original word and a numerical code that represents the word's function within the language.
* The Scanner returns a "tokenized" line to the Parser.

#### Parser - This is about 80% complete.
* Currently verifies that each word received from the Scanner is a valid keyword within the SCL language.
* Statement blocks needs to be recognized and formed to pass to the Interpreter.

#### Interpreter - 0% complete.
* This part of the project will perform the actual operations that the source file requires.
* Will receive statement blocks from the Parser.
