//QueueEmptyException.java
//Aaron Hom
//awhom
//pa4
//Throws exception if Queue is empty

public class QueueEmptyException extends RuntimeException{
   public QueueEmptyException(String s){
      super(s);
   }
}
