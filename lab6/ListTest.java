//ListTest.java
//Aaron Hom
//awhom
//lab 6
//A program for testing List ADT operations

class ListTest{
    public static void main(String[] args){

	int i, j, k;

	//creates lists 
	List<String> A = new List<String>();
	List<String> B = new List<String>();
	List<List<String>> C = new List<List<String>>();

	//checks if lists are empty
	System.out.println("Is A empty? " +A.isEmpty()); //false
        System.out.println("Is B empty? " +B.isEmpty()); //false 
        System.out.println("Is C empty? " +C.isEmpty()); //true

	//print A and B lists
        System.out.println("A: "+A);
        System.out.println("B: "+B);

	//add more pairs to each list 
	A.add(1, "hello");
	A.add(2, "bonjour");
	A.add(3, "hola");
	B.add(1, "bye");
	B.add(2, "au revoir");
	B.add(3, "addio");
	C.add(1, A);
	C.add(2, B);

	//checks size and get functions 
	System.out.println("A's size is: "+ A.size()); //3
        System.out.println("B's size is: "+ B.size()); //3
        System.out.println("C's size is: "+ C.size()); //2
	System.out.println("A get(2) is "+ A.get(2)); //bonjour
	System.out.println("B get(0) is "+ B.get(1)); //bye
        System.out.println("C get(1) is "+ C.get(1)); 

	//print out all 3 lists 
        System.out.println("A: "+A); 
        System.out.println("B: "+B);
        System.out.println("C: "+C);

	//remove 2nd element from A and B
	A.remove(2);
	B.remove(2);

	//prints lists out again
        System.out.println("A: "+A);
        System.out.println("B: "+B);
        System.out.println("C: "+C); 

	//checks size again
        System.out.println("A's size is: "+ A.size()); //2
        System.out.println("B's size is: "+ B.size()); //2
        System.out.println("C's size is: "+ C.size()); //2

	//catches exception
        try{
           System.out.println(A.get(20));
        }catch(ListIndexOutOfBoundsException e){
           System.out.println("Caught Exception: ");
           System.out.println(e);
           System.out.println("Continuing without interuption");
        }

	//clears lists
        A.removeAll();
        B.removeAll();
        C.removeAll();

	//checks if lists are empty
        System.out.println("Is A empty? " +A.isEmpty()); //true
        System.out.println("Is B empty? " +B.isEmpty()); //true
        System.out.println("Is C empty? " +C.isEmpty()); //true
    }
}
