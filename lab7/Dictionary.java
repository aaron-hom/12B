//Dictionary.java
//Aaron Hom
//awhom
//lab7
//Translating the C version of binary search tree to java

// private types and functions ------------------------------------------------

public class Dictionary implements DictionaryInterface{

    //private inner node class
    private class Node{
	Pair item;
        Node left;
        Node right;

	Node(Pair p){
	    item = p;
	    left = null;
	    right = null;
	}
    }

    //private inner pair class
    private class Pair{
	String key;
	String val;

	Pair(String a, String b){
	    key = a;
	    val = b;
	}
    }

    //fields for Dictionary class
    private Node root; //first node in list (like head)
    private int numItems; //number of items

    //Dictionary() constructor
    public Dictionary(){
	root = null;
	numItems = 0;
    }

    //private helper functions ----------------------------------------------------------
    
    //findKey
    //returns Node that has key k in subtree rooted at Q, returns null if no Node exists
    Node findKey(Node P, String target){
	if(P == null || P.item.key.equals(target)){
            return P;
	}
	if(P.item.key.compareToIgnoreCase(target) > 0){
            return findKey(P.left, target);
	}else{
            return findKey(P.right, target);
	}
    }

    //findParent()
    //pre: P != null
    //returns parent of P in subtree root, returns null if P = Q
    Node findParent(Node P, Node Q){
	Node R = null;
	if(P != Q){
	    R = Q;
	    while(R.left != P && R.right != P){
		if(P.item.key.compareToIgnoreCase(P.item.key) < 0){
		    R = R.left;
		}else{
		    R = R.right;
		}
	    }
	}
	return R;
    }

    //findLeftmost()
    //returns leftmost Node in subtree rooted P, returns null is P is empty
    Node findLeftmost(Node P){
	Node Q = P;
	if(Q != null) for( ; Q.left != null; Q = Q.left);
	    return Q;
    }

     //printInOrder()
     //prints the key, val pairs from the subtree rooted at P in ascending order
     void printInOrder(Node P){
         if(P != null){
	     printInOrder(P.left);
	     System.out.println(P.item.key +" "+ P.item.val);
	     printInOrder(P.right);
	 }
     }

     //deleteAll()
     //deletes all Nodes in the subtree rooted at P
     void deleteAll(Node P){
	 if(P != null){
	     deleteAll(P.left);
	     deleteAll(P.right);
	 }
     }
       
     //ADT Operations --------------------------------------------------------------------
	
     //isEmpty()
     //pre: none
     //post: returns true if Dictionary is empty, false otherwise
     public boolean isEmpty(){
         return(numItems == 0);
     }

     //size()
     //pre: none
     //returns size of Dictionary
     public int size(){
         return numItems;
     } 

     //lookup
     //pre: none
     //post: returns val associated key, null if key doesn't exist
     public String lookup(String key){
	 Node P;
	 P = findKey(root, key);
	 return(P == null ? null : P.item.val);
     }

     //insert()
     //inserts new key, val pair into Dictionary
     //pre: lookup(key) == null
     //post: !isEmpty(), size++
     public void insert(String key, String val) throws DuplicateKeyException{
	 Node P, Q, R;
	 if(findKey(root, key) != null){
	     throw new DuplicateKeyException("Dictionary error: cannot insert already existing key");
	 }
	 P = new Node(new Pair(key, val));
	 Q = root;
	 R = null;
	 while(Q != null){
	     R = Q;
	     if(Q.item.key.compareToIgnoreCase(key) > 0){
		 Q = Q.left;
	     }else{
		 Q = Q.right;
	     }
	 }
         if(R == null){
	     root = P;
	 }else if(R.item.key.compareToIgnoreCase(key) > 0){
	     R.left = P;
	 }else{
	     R.right = P;
	 }
	 numItems++;
     }

     //delete()
     //deletes key and associated val
     //pre:lookup(key) != null
     public void delete(String key) throws KeyNotFoundException{
	 Node P, Q, R;
	 if(findKey(root, key) == null){
	     throw new KeyNotFoundException("Dictionary error: cannot delete non existent key");
	 }
	 P = findKey(root, key);
	 if(P.left == null && P.right == null){
	     if(P == root){
		 root = null;
	     }else{
		 Q = findParent(P, root);
		 if(Q.right == P) Q.right = null;
		 else Q.left = null;
	     }
	 }else if(P.right == null){
	     if(P == root){
		 root = P.left;
	     }else{
		 Q = findParent(P, root);
		 if(Q.right == P) Q.right = P.left;
		 else Q.left = P.left;
	     }
	 }else if(P.left == null){
	     if(P == root){
		 root = P.right;
	     }else{
		 Q = findParent(P, root);
		 if(Q.right == P) Q.right = P.right;
		 else Q.left = P.right;
	     }
	 }else{  
	     R = findLeftmost(P.right);
	     P.item.key = R.item.key;
	     P.item.val = R.item.val;
	     Q = findParent(R, P);
	     if(Q.right == R) Q.right = R.right;
	     else Q.left = R.right;
	 }
	 numItems--;
     }

     //makeEmpty()
     //pre: none
     public void makeEmpty(){
         deleteAll(root);
	 root = null;
	 numItems = 0;
     }

     //toString()
     //overrides Object's toString() method
     public String toString(){
         printInOrder(root);
	 return "";
     }
}
