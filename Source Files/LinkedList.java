/*
 * Author: Joel Smith
 * 
 * Custom Linked list data structure for the ability to store both a string and an integer in the same linked list node
 * 
 * 	  Included functions:
 * 	  -getHeadNode() - Returns the node stored in the head class variable for the current object of the LinkedList class
 *    -add() - adds a node to the current linked list with data passed by the parameters num and str, which are an int and String respectively
 * 	  -
 */
public class LinkedList {
	
	//Class variables
	private Node head;
	public int length;
	
	//User defined datatype called Node which stores an integer, string and a pointer to the next node in the list
	class Node{
		public int num;
		public String str;
		public Node next;
	}
	
	//LinkedList class constructor
	public void LinkedListClass() {
		head = null;
		length = 0;
	}
	
	//Returns the head node of the linked list
	public Node getHeadNode() {
		return head;
	}
	
	//Adds a new node to the list with the data passed by the parameters
	public void add(int num, String str) {
		Node temp = new Node();
		temp.num = num;
		temp.str = str;
		temp.next = null;
		
		//Adding for empty linked list
		if (head == null) {
			head = temp;
			this.length = 1;
		}
		
		//Adding for non-empty linked list
		else {
			Node curr = new Node();
			curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = temp;
			this.length++;
		}
	}
	
	
	
	//Prints out the current contents stored in the linked list nodes
	public void display() {
		Node curr = new Node();
		curr = head;
		String Output = "Here is the linked list from the head node till the end";
		while (curr != null) {
			Output += "\n" + curr.num + ", " + curr.str;
			curr = curr.next;
		}
		System.out.print(Output);
	}
	
	//Static function that returns the num value stored in the current node, which is passed by the parameter curr
	public static int getNodeInt(Node curr) {
		return curr.num;
	}
	
	//Static function that returns the str value stored in teh current node, which is passed by the parameter curr
	public static String getNodeStr(Node curr) {
		return curr.str;
	}
	
	//Static function that returns the next node for the current node being passed by the parameter curr
	public static Node NextNode(Node curr) {
		return curr.next;
	}
	
	//Static Linked list function that allows for a LinkedList array to be dynamically increased when given the 
	//original array and the new length of the array, labeled arr and NewLength respectively
	public static LinkedList[] AddList(LinkedList[] arr, int NewLength) {
		LinkedList[] NewArr = new LinkedList[NewLength];
		for(int i = 0; i < arr.length; i++) {
			NewArr[i] = arr[i];
		}
		return NewArr;
	}
}
