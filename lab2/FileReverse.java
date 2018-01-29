//FileReverse.java
//Aaron Hom
//awhom
//lab2
//Takes an input file, reverses the contents and prints result

import java.io.*;
import java.util.Scanner;

class FileReverse{
    public static void main(String[] args) throws IOException{
    Scanner in = null;
    PrintWriter out = null;
    String line = null;
    String[] token = null;
    int i, n, lineNumber = 0;

// check number of command line arguments is at least 2
    if(args.length < 2){
        System.out.println("Usage: FileCopy <input file> <output file>");
        System.exit(1);
    }

// open files
    in = new Scanner(new File(args[0]));
    out = new PrintWriter(new FileWriter(args[1]));

// read lines from in, extract and print tokens from each line
    while( in.hasNextLine() ){
        lineNumber++;

// trim leading and trailing spaces, then add one trailing space so 
// split works on blank lines
        line = in.nextLine().trim() + " "; 
// split line around white space 
        token = line.split("\\s+");  
// print out tokens       
        n = token.length;
        for(i=0; i<n; i++){
            out.println(stringReverse(token[i], token[i].length()));
        }
    }

// close files
in.close();
out.close();
    }

    public static String stringReverse(String s, int n){
	String t = "";
        if (s.length() <=  1){        
            return s;
        }
        if (n > 0){         
	    t = s.charAt(n-1) + stringReverse(s, n-1);
        }
        return t;
        }
    }
