//QueueTest.java
//Aaron Hom
//awhom
//pa4
//Tests individual functions from Queue.java

public class QueueTest{
    public static void main(String[] args){

	Queue X = new Queue();
	System.out.println(X.length()); //true
	System.out.println(X.isEmpty()); //0
	X.enqueue((int)17); //add 17
        X.enqueue((int)38); //add 38
        System.out.println(X.length()); //2
        X.enqueue((int)4); //add 4
        X.enqueue((int)20); //add 20
	System.out.println(X.toString());
	System.out.println(X.isEmpty());
        System.out.println(X.length()); //4
	System.out.println(X.isEmpty());//False
	X.dequeue(); //removes the 17
        System.out.println(X.toString());
	System.out.println(X.peek()); //38
        System.out.println(X.length()); //3
	System.out.println(X.isEmpty()); //False
	X.dequeueAll(); //remove all
        System.out.println(X.isEmpty()); //true
	System.out.println(X.peek()); //tests if exception is thrown	

    }
}
