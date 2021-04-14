import java.util.ArrayList;
//i am importing LinkedList ll containing everything

//currently, i am storing the parsed structure in an ArrayList, with datatype of string
//I am getting the int values from the linkedlist, storing them as a string, and pulling the string values when necessary
//necessary conditions would be defined as: defining a IDENTIFIER


public class ParseLinkedList {

    protected LinkedList in;
    protected ArrayList<String> parsedTree;


    ParseLinkedList (Object LL){
        this.in = (LinkedList) LL;
        parsedTree = new ArrayList<String>();
    }

    public void beginParse(){


        //import parkers linked list
        LinkedList.Node itNode = in.getHeadNode();//firstnode

        if(LinkedList.getNodeInt(itNode) == 21) {
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
            itNode = LinkedList.NextNode(itNode); //2ndnode
            System.out.print("Function scanned \t\t\tToken: 21");
            //iterate to next node
            if(LinkedList.getNodeInt(itNode) == 9000){
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                //get int val of second node and chedck if it is == new line
                itNode = LinkedList.NextNode(itNode);//3rdnode
                //set first node to second node
                if(LinkedList.getNodeInt(itNode) == 24){    //if next keyword is CONST
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                    System.out.println("\nConstant scanned \t\t\tToken: 24");

                    //if next node int == constant
                    itNode = LinkedList.NextNode(itNode); //4thnode
                    if(LinkedList.getNodeInt(itNode) == 9000){  //after const we have a blank line
                        parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                        //see if 4th node is blank t
                        itNode =LinkedList.NextNode(itNode); //5th node
                        //check if next node is DEFINE, if not, move to variables
                        if(LinkedList.getNodeInt(itNode) == 25){ // if next keyword is VARI then no CONST are defined
                            parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                            System.out.print("\nVariable Scanned \t\t\tToken: 25");

                            //see if 5th node is keyword variable
                            //we can hard code the first 5 nodes bc they follow consistent structure
                            itNode = LinkedList.NextNode(itNode); //6thnode
                            // definition functions
                            if(LinkedList.getNodeInt(itNode) == 9000) { //if blank after DEFINE
                                parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                                itNode = LinkedList.NextNode(itNode);
                                //check if define is preceeded by a blank.
                                if (LinkedList.getNodeInt(itNode) == 26) {
                                    //if word = define
                                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                                    itNode = LinkedList.NextNode(itNode);
                                    //begin the definitions,
                                    defineVari(itNode);
                                }
                            }else {blankErr();}
                        }   //after blank line we can have DEFINE,VARI,orBEGIN
                        else if(LinkedList.getNodeInt(itNode) ==26){ //if after CONST we have DEFINE, define the constants
                            parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                            //see if 5th node is keyword variable
                            //we can hard code the first 5 nodes bc they follow consistent structure
                            itNode = LinkedList.NextNode(itNode); //6thnode
                            //next node shld be 9000, maybe check here or check in the define method
                            defineVari(itNode);
                        }
                        else if(LinkedList.getNodeInt(itNode) ==22){ //After CONST we dont need to define or need variables, but we need begin(22)
                            parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                            //begin the definitions,
                            System.out.println("Begin scanned \t\t\tToken: 22");

                            itNode = LinkedList.NextNode(itNode); //if yes increase node val and begin
                            begin(itNode);
                        } //after funct we can have const var or begin (BEGIN COMPLETED)//if after const, we have define keyword
                        else{ errorMsg("must have define, variable, or begin keyword following constants keyword");}
                    }
                    else {blankErr();}
                }
                else if(LinkedList.getNodeInt(itNode) ==25){ //After function, we dont NEED CONST, but we can have VARI
                    //figure out what to do if we have VARi w/ no CONST
                    System.out.print("\nVariable Scanned \t\t\tToken: 25");
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                    //see if 5th node is keyword variable
                    //we can hard code the first 5 nodes bc they follow consistent structure
                    itNode = LinkedList.NextNode(itNode); //6thnode
                    // definition functions
                    if(LinkedList.getNodeInt(itNode) == 9000) { //if blank after DEFINE
                        parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                        itNode = LinkedList.NextNode(itNode);
                        //check if define is preceeded by a blank.
                        if (LinkedList.getNodeInt(itNode) == 26) {
                            //if word = define
                            parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                            itNode = LinkedList.NextNode(itNode);
                            //begin the definitions,
                            defineVari(itNode);
                        }
                    }else {blankErr();}
                }
                else if(LinkedList.getNodeInt(itNode) ==22){ //After Function, we dont NEED const or variables, but we need begin(22)
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                    System.out.println("Begin scanned  \t\t\tToken: 22");

                    //begin the definitions,
                    itNode = LinkedList.NextNode(itNode); //if yes increase node val and begin
                    begin(itNode);
                } //after funct we can have const var or begin (BEGIN COMPLETED)
                else{ errorMsg("must have constant, variable, or begin following function keyword");} //after blank and funct must have proper key words
            }
            else{blankErr();} //blank must follow function
        }
        else{
            errorMsg("must begin program with function.");
        }
    }

    //no blanks :O?
    public void blankErr(){
        String error = "Error: must include newline/space ";

        System.err.println(error);
    }

    //customizalbe error mesage
    public void errorMsg(String str){
        String error = "Error: ";

        System.err.println(error+str);
    }

    public void defineVari(LinkedList.Node node) {

       
        if(LinkedList.getNodeInt(node) == 42 ){
            //||LinkedList.getNodeInt(node) == 43 ||LinkedList.getNodeInt(node) == 44) {
            //followig define, we have an identifier, depending on if it is a string(42),
            // ignore this, should be only 42 but i left it in justin case int(43), or double, we add it to the tree
            parsedTree.add(LinkedList.getNodeStr(node));
            //store the string value of the node rather than the int
            node = LinkedList.NextNode(node);
            if(LinkedList.getNodeInt(node) == 28){
                //if the next keyword is TYPE
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                node = LinkedList.NextNode(node); //iterate
                datatype(node);

            }else{errorMsg("Missing keyword TYPE");}

        }else{errorMsg("No identifier");}

    }

    public void datatype(LinkedList.Node datanode){

        switch(LinkedList.getNodeInt(datanode)){

            case 31: //integer datatype
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(datanode)));
                datanode = LinkedList.NextNode(datanode);
                if (LinkedList.getNodeInt(datanode)==9000) {
                    System.out.print("\nDefine Variable Scanned \tTokens: 26 42 28 31");

                    actions(datanode); //recursively call the define variable method after increasing node value
                }
                else if (LinkedList.getNodeInt(datanode)==5){ //if you have eqOP, you are defining something after type, we can be firm with these options bc datatype() is only called in defining
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(datanode)));
                    datanode=LinkedList.NextNode(datanode);
                    System.out.print("Define Constant Scanned \tTokens: 26 42 28 31");

                    eqOP(datanode);
                    //send the value to eqOp. if the value after is 9000
                }
                //Ok, so after finding the datatype, we can either leave it at that(variables) or have eqOP(5)

                break; //iterate

            case 32: //double datatype
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(datanode)));
                datanode = LinkedList.NextNode(datanode);
                defineVari(datanode);
                break; //iterate

            case 33: //boolean datatype
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(datanode)));
                datanode = LinkedList.NextNode(datanode);
                defineVari(datanode);
                break; //iterate

            case 34: //char datatype
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(datanode)));
                datanode = LinkedList.NextNode(datanode);
                defineVari(datanode);
                break; //iterate

            default:
                errorMsg("Invalid datatype keyword");

                break;
        }

    }

    private void begin(LinkedList.Node node){

        if (LinkedList.getNodeInt(node) != 23){ //if node != ENDFUN loop through actions
            System.out.print("\nBegin Scanned  \t\t\t\tToken: 22");

            actions(node);
        } else if (LinkedList.getNodeInt(node) == 23) {
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node = LinkedList.NextNode(node);
            System.out.println("EndFUN scanned  \t\t\tToken: 23");

            System.out.println("\nCompleted Parsing");
        }
        else { errorMsg("invalid keyword");}
    }
    //define teh action methods
    public void actions(LinkedList.Node node) {

        //receive node value (first runthru should be right after Begin)

        if (LinkedList.getNodeInt(node) == 9000){
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node = LinkedList.NextNode(node);
            switch (LinkedList.getNodeInt(node)) {
                case 22:
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    begin(node);
                    break;
                case 23:
                    //endFUN catch
                    begin(node);

                    break;
                case 25:
                    //if Variables keyword is sent to actions() use method from first function
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    if (LinkedList.getNodeInt(node) == 9000){
                        parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                        node = LinkedList.NextNode(node);
                        if(LinkedList.getNodeInt(node) ==26){ //if after VARI we have DEFINE, define
                            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                            node = LinkedList.NextNode(node); //6thnode
                            System.out.print("\nVariable Scanned \t\t\tToken: 25");
                            //next node shld be 9000, maybe check here or check in the define method
                            defineVari(node);
                        }
                        else if(LinkedList.getNodeInt(node) ==22){ //After VARI we dont need to define or need variables, but we need begin(22)
                            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                            System.out.print("\nVariable Scanned not defined");

                            //begin the definitions,
                            node = LinkedList.NextNode(node); //if yes increase node val and begin
                            begin(node);
                        } //after  var we can have define or begin

                        else {errorMsg("Invalid keyword following Variables");}

                    } //After Variables, there should aways be a blank line
                    else{blankErr();} //if no blank line
                    break;
                case 26:
                    //define action
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);


                    defineVari(node);
                    break;
                case 27:
                    //set action
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    set(node);

                    break;
                case 29:
                    //Display action
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    System.out.println("display scanned \t\t\tToken: 29 42");

                    display(node);
                    break;
                case 37:
                    //define if - then action
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    ifStmt(node);
                    // ifStatement();
                    break;

                case 39:
                    //define endif
                    System.out.println("\nENDIF Scanned \t\t\t\tToken: 39");
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    actions(node);

                    break;
                case 40:
                    //define ELSEIF() (optional)
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    System.out.println("ELSE-IF Scanned \t\t\t\tToken: 40");
                    elseIfStmt(node);
                    break;
                case 41:
                    //define ELSE
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    System.out.print("ELSE Scanned  \t\t\t\tToken: 41");
                    actions(node); //send through actions loop to determine which action needs to be taken
                    break;
                default:
                    errorMsg("Invalid word to begin a statement");
                    break;
            }
        }
    }

    private void display(LinkedList.Node node) {
        if(LinkedList.getNodeInt(node) == 42){
            //if there is variable identifier in CHAR format, add the literal string to tree
            parsedTree.add(LinkedList.getNodeStr(node));
            node = LinkedList.NextNode(node);
            MathOP(node);
        }
        else{errorMsg("no reference variable");}

    }


    public void set(LinkedList.Node node){
        //the node we import should contain int val of 42 for variable
        if(LinkedList.getNodeInt(node) == 42){
            System.out.print("\nSet Action Scanned  \t\tTokens: 27 42");
            //if there is variable identifier in CHAR format, add the literal string to tree
            parsedTree.add(LinkedList.getNodeStr(node));
            node = LinkedList.NextNode(node);
            MathOP(node);
        }
        else{errorMsg("no reference variable");}
    }
    private void eqOP(LinkedList.Node node) {
        switch (LinkedList.getNodeInt(node)) {

            case 43: { //if adding a NumericalDatatype
                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 5 43");
                //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                MathOP(node);
                break;
            }
            case 42: { //if adding identifier

                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 5 42");
                //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                MathOP(node); //9000 catches end of set in MAthOP()
                break;
            }
            default: {errorMsg("improper operator");}
        }
    }

    private void MathOP(LinkedList.Node node){
        //we import node value to determine the math operation performed

        switch (LinkedList.getNodeInt(node)) {

            case 1: //AddOP
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));//add the addOP token
                node = LinkedList.NextNode(node);
                addOP(node);
                break;
            case 2: //SubOP
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));//add the addOP token
                node = LinkedList.NextNode(node);
                subOP(node);
                break;
            case 3: //MultOP
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));//add the addOP token
                node = LinkedList.NextNode(node);
                multOP(node);

                break;
            case 4: //DivOP

                break;
            case 5: //=OP
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                node = LinkedList.NextNode(node);
                eqOP(node);

                break;
            case 6: //==OP

                break;
            case 7: //LessThanOP
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                node = LinkedList.NextNode(node);
                LThanOP(node);
                break;
            case 8: //GreatThanOP

                break;
            case 9: //GThanEqOP
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                node = LinkedList.NextNode(node);
                GThanEqOP(node);
                break;
            case 10: //LThanEqOP

                break;
            case 11: //NotOP

                break;
            case 12: //NotEq!=OP

                break;
            case 14: //closed parenthesis also containing THEN
                closeIfs(node);

                break;

            case 9000: //catch for end of math operation
                //send to actions()
                actions(node);
                break;

            default:
                errorMsg("Unknown mathematical operation");
                break;
        }
    }

    private void LThanOP(LinkedList.Node node) {
        switch (LinkedList.getNodeInt(node)) {

            case 43: { //if adding a NumericalDatatype
                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 7 43");
                //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                MathOP(node);
                break;
            }
            case 42: { //if adding identifier

                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 7 42");
                //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                MathOP(node);
                break;
            }
        }
    }

    private void multOP(LinkedList.Node node) {
        switch (LinkedList.getNodeInt(node)) {

            case 43: { //if adding a NumericalDatatype
                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 3 43");
                //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                MathOP(node);
                break;
            }
            case 42: { //if adding identifier

                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 3 42");
                //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                MathOP(node);
                break;
            }
            default: {errorMsg("improper operator");}
        }
    }

    private void closeIfs(LinkedList.Node node){
        if(LinkedList.getNodeInt(node)==14) {
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node = LinkedList.NextNode(node);
            if(LinkedList.getNodeInt(node)==38){ //verify that IF contains THEN, THEN is followed by 9000
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                System.out.print(" 14 38");
                node = LinkedList.NextNode(node);


                actions(node); //loop through mathOP to detrmine next operation
            } else if(LinkedList.getNodeInt(node)== 41){ //verify if ELSEIF contains else,  ELSE is followed by 9000
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                System.out.println("ElseIf Scanned  tToken: 40");

                node = LinkedList.NextNode(node);
                MathOP(node); //after verifying ELSEIF contains ELSE, send thru mathOP to next iteration
            }

            else{errorMsg("Missing required keyword");}


        }else{errorMsg("Missing closed parenthesis");}
    }

    private void subOP(LinkedList.Node node) {
        switch (LinkedList.getNodeInt(node)) {

            case 43: { //if adding a NumericalDatatype
                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 2 43");
                //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                MathOP(node);
                break;
            }
            case 42: { //if adding identifier

                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 2 42");
                //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                MathOP(node);
                break;
            }
            default: {errorMsg("improper operator");}
        }
    }

    private void GThanEqOP(LinkedList.Node node) { //currently only allows input of 1 to 1 comparison
        switch (LinkedList.getNodeInt(node)) {

            case 43: { //if adding a NumericalDatatype
                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 9 43");
                //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                MathOP(node);
                break;
            }
            case 42: { //if adding identifier

                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 9 42");
                //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                MathOP(node);
                break;
            }
        }
    }

    private void addOP(LinkedList.Node node) {
        switch (LinkedList.getNodeInt(node)) {

            case 43: { //if adding a NumericalDatatype
                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                node = LinkedList.NextNode(node);
                System.out.print(" 1 43");
                //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                MathOP(node);
                break;
            }
            case 42: { //if adding identifier

                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                node = LinkedList.NextNode(node);
                //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                System.out.print(" 1 42");
                MathOP(node);

                break;
            }
            default: {errorMsg("improper operator");}
        }
    }

    private void elseIfStmt(LinkedList.Node node) {
        if(LinkedList.getNodeInt(node)==13){
            //after if need open parenthesis
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node= LinkedList.NextNode(node);
            switch (LinkedList.getNodeInt(node)) {

                case 14: { //elseIF is optional, if closed parenthesis immediately follows then add to tree and move fwd

                    closeIfs(node);//send to close ifs to verify if contains required ELSE keyword

                }
                case 43: { //if adding a NumericalDatatype
                    parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                    node = LinkedList.NextNode(node);
                    //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                    MathOP(node);
                    break;
                }
                case 42: { //if adding identifier

                    parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                    node = LinkedList.NextNode(node);
                    //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                    MathOP(node);
                    break;

                }
                default: {errorMsg("improper comparison operator");}
            }

        }else{errorMsg("Need parenthesis after ELSEIF");}

    }
    private void ifStmt(LinkedList.Node node){
        if(LinkedList.getNodeInt(node)==13){
            //after if need parenthesis
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node = LinkedList.NextNode(node);
            switch (LinkedList.getNodeInt(node)) {

                case 43: { //if adding a NumericalDatatype
                    parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of int to ParseTree
                    node = LinkedList.NextNode(node);
                    //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                    System.out.print("\nIf - Then scanned  \t\t\tTokens: 37 13 43");
                    MathOP(node);
                    break;
                }
                case 42: { //if adding identifier

                    parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                    node = LinkedList.NextNode(node);
                    System.out.print("\nIf - Then scanned  \t\t\tTokens: 37 13 42");
                    //so here i am implying that whenever you have an identifier, you are going to have IDENTIFIER = IDENTIFIER MATHOP VALUE instead of simply IDENTIFIER = IDENTIFIER
                    MathOP(node);
                    break;

                }
                default: {errorMsg("improper comparison operator");}
            }
        }else{errorMsg("Need parenthesis after IF");}
    }



}
