//Search.java
//Aaron Hom
//awhom
//prog2
//Searches an input file for certain key words, returning line # if found

import java.io.*;
import java.util.Scanner;

class Search{
    public static void main(String[] args) throws IOException{
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        int i, n, lineNumber = 0;
        // check number of command line arguments is at least 2
        if(args.length < 2){
            System.out.println("Usage: Search file target1 [target2 ..]");
            System.exit(1);
	}

	// open files
        in = new Scanner(new File(args[0]));
	// count the number of lines in file
	int lineCount = 0;
	while( in.hasNextLine() ){
	    in.nextLine();
	    lineCount++;
	}
	in.close();
        
	// makes array to correspond to the line number
	int[] LN = new int[lineCount];
	for(i=0; i < LN.length; i++){
	    LN[i] = i+1;
	}

	String[] word = new String[lineCount];
        // open files
        in = new Scanner(new File(args[0]));
        // read lines from in, extract and print tokens from each line
        while( in.hasNextLine() ){
            word[lineNumber++] = in.nextLine();
	}
	//calls mergeSort 
	mergeSort(word, LN, 0, word.length-1);
	
	//for loop to test if a word was found or not
        for(i=1; i < args.length; i++){
            int num = binarySearch(word, 0, word.length-1, args[i]);
	    //if not found
	    if(num == -1){
		System.out.println(args[i]+" not found");
	    //if found
	    }else{
                 System.out.println(args[i]+" found on line "+ LN[num]);
		 }      	    
	    }
       }

//finds target string in sorted array
public static int binarySearch(String[] word, int p, int r, String target){
    int q;
    if(p > r) {
        return -1;
    }else{
	q = (p+r)/2;
        if(target.compareTo(word[q]) == 0){
	    return q;
	}else if(target.compareTo(word[q]) < 0){
	    return binarySearch(word, p, q-1, target);
	}else{ 
	    return binarySearch(word, q+1, r, target);
	     }
    }
}

//splits string array and recursively sorts each half
static void mergeSort(String[] word, int[] lineNumber, int p, int r){
    int q;
    if (p < r){
	q = (p+r)/2;
	mergeSort(word, lineNumber, p, q);
	mergeSort(word, lineNumber, q+1, r);
	merge(word, lineNumber, p, q, r);
    }
}

//combines both halves of the string array
static void merge(String[] word, int[] lineNumber, int p, int q, int r){
    int temp1 = q-p+1;
    int temp2 = r-q;
    String[] L = new String[temp1];
    String[] R = new String[temp2];
    int[] X = new int[temp1]; 
    int[] Y = new int[temp2]; 
    int i, j, k;

    for(i=0; i<temp1; i++) L[i] = word[p+i];
    for(i=0; i<temp1; i++) X[i] = lineNumber[p+i];
    for(j=0; j<temp2; j++) R[j] = word[q+j+1];
    for(j=0; j<temp2; j++) Y[j] = lineNumber[q+j+1];
    i = 0; j = 0;

    for(k=p; k<=r; k++){
	if( i<temp1 && j<temp2 ){
            if( L[i].compareTo(R[j]) < 0 ){
		word[k] = L[i];
		lineNumber[k] = X[i];
		i++;
            }else{
		word[k] = R[j];
		lineNumber[k] = Y[j];
		j++;
	    }
	}else if( i<temp1 ){
            word[k] = L[i];
	    lineNumber[k] = X[i];
            i++;
	}else{
            word[k] = R[j];
	    lineNumber[k] = Y[j];
            j++;
	}
        }
    }  
}

