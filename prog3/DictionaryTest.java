//DictionaryTest.java
//Aaron Hom
//awhom
//Program 3
//Serves as test client for Dictionary ADT

public class DictionaryTest{
    public static void main(String[] args){
        Dictionary book = new Dictionary();
	System.out.println(book.isEmpty());
	System.out.println(book.size());
 	book.insert("4", "hello");
	System.out.println(book.isEmpty());
	System.out.println(book.size());
	book.insert("2", "hola");
	book.insert("5", "hallo");
	System.out.println(book.isEmpty());
        System.out.println(book.size());
	book.delete("5");
	System.out.println(book.size());
	//book.insert("2", "ni hao"); //throws DuplicateKeyException
	//book.delete("7"); //throws NoKeyFoundException
	book.insert("3", "czesc");
        book.insert("1", "shalom");
	System.out.println(book.size());
	System.out.println(book);
	System.out.println(book.toString());
	book.makeEmpty();
	System.out.println(book.isEmpty());
	System.out.println(book.size());

    }
}

