//Recursion.java
//Aaron Hom
//awhom
//pa1
//Echoes array, prints max and min index reverses array using 3 different sorting methods

class Recursion {
   
    // reverseArray1()
    // Places the leftmost n elements of X[] into the rightmost n positions in
    // Y[] in reverse order
    static void reverseArray1(int[] X, int n, int[] Y){
	if (n > 0){
	    Y[Y.length-n] = X[n-1];
	    reverseArray1(X, n-1, Y);
        }
    }

    // reverseArray2()
    // Places the rightmost n elements of X[] into the leftmost n positions in
    // Y[] in reverse order.
    static void reverseArray2(int[] X, int n, int[] Y){
	if (n > 0){
	    Y[n-1] = X[X.length-n];
	    reverseArray2(X, n-1, Y);
        }
    }
   
    // reverseArray3()
    // Reverses the subarray X[i...j].
    static void reverseArray3(int[] X, int i, int j){
	int temp;
	if (i<j){
	    temp = X[j]; 
	    X[j] = X[i];
	    X[i] = temp;
	    reverseArray3(X, i+1, j-1);
	}
    }
     
    // maxArrayIndex()
    // returns the index of the largest value in int array X
    static int maxArrayIndex(int[] X, int p, int r){
        int q = 0;
        if (p < r) {
            q = (p + r) / 2;
            int lMax = maxArrayIndex(X, p, q);
            int rMax = maxArrayIndex(X, q + 1, r);
            int endMax = max(X, lMax, rMax);
            return endMax;
        }
        return r;
    }

    //compares array index values to each other and returns bigger value
    static int max(int[] X, int p, int r) {
        int max = 0;
        if (X[p] > X[r]) {
            max = p;
        } else {
            max = r;
        }
        return max;
    }
    // minArrayIndex()
    // returns the index of the smallest value in int array X
    static int minArrayIndex(int[] X, int p, int r){
	int q = 0;
        if (p < r) {
            q = (p + r) / 2;
            int lMin = minArrayIndex(X, p, q);
            int rMin = minArrayIndex(X, q + 1, r);
            int endMin = min(X, lMin, rMin);
            return endMin;
        }
        return r;
    }

    //compares array index values to each other and returns smaller value
    static int min(int[] X, int p, int r) {
        int min = 0;
        if (X[p] < X[r]) {
            min = p;
        } else {
            min = r;
        }
        return min;
    }
 
    // main()
    public static void main(String[] args){
        //initializes arrays and values
	int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
	int[] B = new int[A.length];
	int[] C = new int[A.length];
	int minIndex = minArrayIndex(A, 0, A.length-1);
	int maxIndex = maxArrayIndex(A, 0, A.length-1);
        
        //echoes array A
	for(int x: A) System.out.print(x+" ");
	System.out.println();
 
        //prints out max and min index
	System.out.println( "minIndex = " + minIndex );  
	System.out.println( "maxIndex = " + maxIndex );
  
	//reverses array A using 3 different methods and prints
	reverseArray1(A, A.length, B);
	for(int x: B) System.out.print(x+" ");
	System.out.println();
	
	reverseArray2(A, A.length, C);
	for(int x: C) System.out.print(x+" ");
	System.out.println();
	
	reverseArray3(A, 0, A.length-1);
	for(int x: A) System.out.print(x+" ");
	System.out.println();  
    }
}
