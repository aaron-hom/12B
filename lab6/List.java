//List.java
//Aaron Hom
//awhom
//lab 6
//A list ADT used to learn about Generics
@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T>{

    // private inner Node class
    private class Node {
	T item;
	Node next;

	Node(T x){
	    item = x;
	    next = null;
	}
    }

    // Fields for the List class
    private Node head;     // reference to first Node in List
    private int numItems;  // number of items in this List

    // IntegerList()
    // constructor for the IntegerList class
    public List(){
	head = null;
	numItems = 0;
    }


    // private helper function -------------------------------------------------

    // find()
    // returns a reference to the Node at position index in this List
    private Node find(int index){
	Node P = head;
	for(int i=1; i<index; i++){
	    P = P.next;
	}
	return P;
    }


    // ADT operations ----------------------------------------------------------

    // isEmpty()
    // pre: none
    // post: returns true if this List is empty, false otherwise
    public boolean isEmpty(){
	return(numItems == 0);
    }

    // size()
    // pre: none
    // post: returns the number of elements in this List
    public int size(){
	return numItems;
    }

    // get()
    // pre: 1 <= index <= size()
    // post: returns item at position index in this List
    public T get(int index) throws ListIndexOutOfBoundsException{
      
	if( index<1 || index>numItems ){
	    throw new ListIndexOutOfBoundsException("List Error: get() called on invalid index: " + index);
	}
	Node P = find(index);
	return P.item;
    }

    // add()
    // inserts newItem into this List at position index
    // pre: 1 <= index <= size()+1
    // post: !isEmpty(), items to the right of newItem are renumbered
    public void add(int index, T newItem) 
	throws ListIndexOutOfBoundsException{
      
	if( index<1 || index>(numItems+1) ){
	    throw new ListIndexOutOfBoundsException("List Error: add() called on invalid index: " + index);
	}
	if(index==1){
	    Node P = new Node(newItem);
	    P.next = head;
	    head = P;
	}else{
	    Node Q = find(index-1); // at this point index >= 2
	    Node R = Q.next;
	    Q.next = new Node(newItem);
	    Q = Q.next;
	    Q.next = R;
	}
	numItems++;
    }

    // remove()
    // deletes item at position index from this List
    // pre: 1 <= index <= size()
    // post: items to the right of deleted item are renumbered
    public void remove(int index)
	throws ListIndexOutOfBoundsException{
	if( index<1 || index>numItems ){
	    throw new ListIndexOutOfBoundsException(
						    "List Error: remove() called on invalid index: " + index);
	}
	if(index==1){
	    Node P = head;
	    head = head.next;
	    P.next = null;
	}else{
	    Node Q = find(index-1);
	    Node R = Q.next;
	    Q.next = R.next;
	    R.next = null;
	}
	numItems--;
    }

    // removeAll()
    // pre: none
    // post: isEmpty()
    public void removeAll(){
	head = null;
	numItems = 0;
    }

    // toString()
    // pre: none
    // post:  prints current state to stdout
    // Overrides Object's toString() method
    public String toString(){
	StringBuffer sb = new StringBuffer();
	Node P = head;

	for( ; P!=null; P=P.next){
	    sb.append(P.item).append(" ");
	}
	return new String(sb);
    }

    // equals()
    // pre: none
    // post: returns true if this IntegerList matches rhs, false otherwise
    // Overrides Object's equals() method
    @SuppressWarnings("unchecked")
    public boolean equals(Object rhs){
	boolean eq = false;
	List<T> L = null;
	Node P = null;
	Node Q = null;

        if(this.getClass()==rhs.getClass()){
	    L = (List<T>)rhs;
	    eq = ( this.numItems == L.numItems );
	    P = this.head;
	    Q = L.head;

	    while(eq && P!=null){
		eq = (P.item == Q.item);
		P = P.next;
		Q = Q.next;
	    }
	}
	return eq;
    }
}
