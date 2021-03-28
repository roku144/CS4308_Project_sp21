


import java.util.ArrayList;
//i am importing LinkedList ll containing everything

//currently, i am storing the parsed structure in an ArrayList, with datatype of string
//I am getting the int values from the linkedlist, storing them as a string, and pulling the string values when necessary
//necessary conditions would be defined as: defining a IDENTIFIER


public class ParseLinkedList extends LinkedList{

    private LinkedList in;
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
                //iterate to next node
            if(LinkedList.getNodeInt(itNode) == 9000){
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                //get int val of second node and chedck if it is == new line
                itNode = LinkedList.NextNode(itNode);//3rdnode
                //set first node to second node
                if(LinkedList.getNodeInt(itNode) == 24){
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                    //if next node int == constant
                    itNode = LinkedList.NextNode(itNode); //4thnode
                    if(LinkedList.getNodeInt(itNode) == 9000){
                        parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                        //see if 4th node is blank
                        itNode =LinkedList.NextNode(itNode); //5th node
                        if(LinkedList.getNodeInt(itNode) == 25){
                            parsedTree.add(String.valueOf(LinkedList.getNodeInt(itNode)));
                            //see if 5th node is keyword variable
                            //we can hard code the first 5 nodes bc they follow consistent structure
                            itNode = LinkedList.NextNode(itNode); //6thnode
                                 defineVari(itNode);

                                 // definition functions



                        } else{ errorMsg("must have variables keyword following constants keyword");}
                    }
                    else {blankErr();}

                }

                else{ errorMsg("must have constant following function keyword");} //after blank and funct must have const
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


        if(LinkedList.getNodeInt(node) == 9000) {
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            //begin the definitions,
            node = LinkedList.NextNode(node);
            //check if define is preceeded by a blank.
          if (LinkedList.getNodeInt(node) == 26) {
            //if word = define
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node =LinkedList.NextNode(node);
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
          else if (LinkedList.getNodeInt(node) ==22){ //after recursively parsing variables, check to see if next node is Begin keyword
              parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
              //begin the definitions,
              node = LinkedList.NextNode(node); //if yes increase node val and begin
              begin(node);
          }
          else {
            errorMsg("Define keyword missing");
        }

        }


        else {errorMsg("Missing new line or begin keyword");}
    }

    public void datatype(LinkedList.Node datanode){

        switch(LinkedList.getNodeInt(datanode)){

            case 31: //integer datatype
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(datanode)));
                datanode = LinkedList.NextNode(datanode);
                defineVari(datanode); //recursively call the define variable method after increasing node value
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
            actions(node);
        } else if (LinkedList.getNodeInt(node) == 23) {
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node = LinkedList.NextNode(node);
            System.out.println("Completed Parsing");
        }
        else { errorMsg("invalid keyword");}
    }
    //define teh action methods
    public void actions(LinkedList.Node node) {

        //receive node value (first runthru should be right after Begin)

        if (LinkedList.getNodeInt(node) == 9000) {
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node = LinkedList.NextNode(node);
            switch (LinkedList.getNodeInt(node)) {
                case 23:

                    //endFUN catch
                    begin(node);
                    break;
                case 27:
                    //set action
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    set(node);
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
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    actions(node);
                    break;
                case 40:
                    //define ELSEIF() (optional)
                    parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                    node = LinkedList.NextNode(node);
                    elseIfStmt(node);
                    
                case 41:
                    //define ELSE
                   parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                   node = LinkedList.NextNode(node);
                   actions(node); //send through actions loop to determine which action needs to be taken
                   break;
                default:
                    errorMsg("Invalid word to begin a statement");
                    break;
            }
        }
    }



    public void set(LinkedList.Node node){
        //the node we import should contain int val of 42 for variable
        if(LinkedList.getNodeInt(node) == 42){
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
                //if you have numerical value, you have IDENTIFIER = Num,Value and the operation is completed, instead of IDENTIFIER = Num,Value MathOP (Num,Value||IDENTIFIER) // ig thats jus the syntax?
                MathOP(node);
                break;
            }
            case 42: { //if adding identifier

                parsedTree.add(LinkedList.getNodeStr(node)); //add literal value of string to ParseTree
                node = LinkedList.NextNode(node);
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

    private void closeIfs(LinkedList.Node node){
        if(LinkedList.getNodeInt(node)==14) {
            parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
            node = LinkedList.NextNode(node);
            if(LinkedList.getNodeInt(node)==38){ //verify that IF contains THEN, THEN is followed by 9000
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
                node = LinkedList.NextNode(node);
                MathOP(node); //loop through mathOP to detrmine next operation
            } else if(LinkedList.getNodeInt(node)== 41){ //verify if ELSEIF contains else,  ELSE is followed by 9000
                parsedTree.add(String.valueOf(LinkedList.getNodeInt(node)));
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
            default: {errorMsg("improper operator");}
        }
    }

    private void GThanEqOP(LinkedList.Node node) { //currently only allows input of 1 to 1 comparison
        switch (LinkedList.getNodeInt(node)) {

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
        }
    }

    private void addOP(LinkedList.Node node) {
        switch (LinkedList.getNodeInt(node)) {

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
        }else{errorMsg("Need parenthesis after IF");}
    }



}
