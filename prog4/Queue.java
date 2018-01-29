//Queue.java
//Aaron Hom
//awhom
//pa4
//Implementation file for the Queue ADT 

public class Queue implements QueueInterface{

    private class Node{
	Object item;
	Node next;

    Node(Object item){
	this.item = item;
	next = null;
    }
}

    private Node first;
    private Node last;
    private int numItems;

   // Queue()
   // default constructor for the Queue class
    Queue(){
        first = null;
        last = null;
        numItems = 0;
    }

   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   //lenth()
   //pre: none
   //post: returns length of Queue
    public int length(){
	return numItems;
    }

   // enqueue()
   // adds object to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem){
       if(first == null){
	   first = new Node(newItem);
           numItems++;
       }else{
	   Node P = first;
	   while(P.next != null){
	       P = P.next;
	   }
	   P.next = new Node(newItem);
	   last = P.next;
           numItems++;
       }
   }

   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element
   public Object dequeue() throws QueueEmptyException{
       if(isEmpty()){
           throw new QueueEmptyException("cannot dequeue() empty queue");
       }else{
           Node P = first;
           first = P.next; 
           numItems--;
           return P.item;
       }
    }

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() throws QueueEmptyException{
      if(first == null){
         throw new QueueEmptyException("cannot peek() empty queue");
      }
      Node P = first;
      return P.item;
   }

   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      if(first == null){
         throw new QueueEmptyException("cannot dequeueAll() empty queue");
      }
      first = null;
      last = null;
      numItems = 0;
   }

   // toString()
   // overrides Object's toString() method
   public String toString(){
       String s = "";
       Node P = first;
       while(P != null){
	   s = s + P.item + " ";
	   P = P.next;
       }   
       return s;
   }   
}
