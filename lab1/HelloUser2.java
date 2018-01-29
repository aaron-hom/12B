//HelloUser2.java
//Aaron Hom
//awhom
//lab1
//Greets user.

    class HelloUser2{
    public static void main( String[] args ){
        String userName = System.getProperty("user.name");

        System.out.println("Hello "+userName+",");
        System.out.println("Welcome to CMPS 12B.");
        System.out.println("Enjoy your quarter!");
    }
}
