//DuplicateKeyException.java
//Aaron Hom
//awhom
//Program 3
//Exception for when there is a duplicate key

public class DuplicateKeyException extends RuntimeException{
    public DuplicateKeyException(String s){
	super(s);
    }
}
