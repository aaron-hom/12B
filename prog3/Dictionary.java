//Dictionary.java
//Aaron Hom
//awhom
//Program 3
//Implements Dictionary ADT based on a linked data structure

public class Dictionary implements DictionaryInterface{

    // private inner Node class
    private class Node {
        String key;
        String val;
        Node next;

        Node(String key, String value){
            this.key = key;
	    this.val = value;
            next = null;
        }
    }
    
    // Fields for the Dictionary class
    private Node head;     // reference to first Node in Dictionary
    private int numItems;  // number of items in this Dictionary

    // Dictionary()
    // constructor for the Dictionary class
    public Dictionary(){
       head = null;
       numItems = 0;
    }

    // private helper function -------------------------------------------------

    // findKey()
    // returns a reference to the Node at position index in this Dictionary
    private Node findKey(String key){
	Node P = head;
	while(P != null){
	    if(P.key.equals(key)){
		return P;
	    }else{
	        P = P.next;
	    }
	}
	    return null;
    }

    // isEmpty()
    // pre: none
    // post: returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty(){
	return(numItems == 0);
    }

    // size()
    // pre: none
    // post: returns the number of elements in this Dictionary
    public int size() {
	return numItems;
    }

    // lookup()
    //pre: none
    // post: returns value from key in this Dictionary
    public String lookup(String key){
        Node P = head;
        while(P != null){
	    if(P.key.equals(key)){
                return P.val;
            }
	    P = P.next;
	}
        return null;
        }

    // insert()
    // inserts string pair(key, value) into this Dictionary at head 
    // pre: lookup(key) == null (no same key exists in Dictionary)
    // post: !isEmpty(), items to the right of key are moved right
    public void insert(String key, String value) throws DuplicateKeyException{
	if(lookup(key) != null){
	    throw new DuplicateKeyException("Dictionary Error: Cannot insert duplicate keys");
	}else if(head == null){
	    Node P = new Node(key, value);;
	    head = P;
	    numItems++;
	}else{
	    Node P = head;
	    while(P != null){
		if(P.next == null){
		    break;
		}
		P = P.next;
	    }
	    P.next = new Node(key, value);
	    numItems++;
        }
    }

    // delete()
    // deletes string pair (key, value) from this Dictionary
    // pre: if lookup(key) != null (key has to exist)
    // post: items to the right of deleted item are moved left
    public void delete(String key) throws KeyNotFoundException{
	if(lookup(key) == null){
	    throw new KeyNotFoundException("Dictionary Error: Cannot delete non-existent key");

        }else{
            if(numItems <= 1){
                Node P = head;
                head = head.next;
                P.next = null;
                numItems--;	
            }else{
                Node P = head;
                if(P.key.equals(key)){
                    head = P.next;
                    numItems--;
	        }else{
	            while(!P.next.key.equals(key)){
		        P = P.next;
	            }
	        P.next = P.next.next;
	        numItems--;
                }
	    }
	}
    }

    // makeEmpty()
    // pre: none
    // post: isEmpty() (empties Dictionary)
    public void makeEmpty(){
	head = null;
	numItems = 0;
    }

    // toString()
    // pre: none
    // post: prints current state to stdout
    public String toString(){
	StringBuilder sb = new StringBuilder();
	//prints Dictionary from head onward
	for(Node P = head ; P!=null; P = P.next){
	    sb.append(P.key).append(" ").append(P.val).append("\n");
	}
	return new String(sb);
    }
}
