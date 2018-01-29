//Simulation.java
//Aaron Hom
//awhom
//pa4
//A program built to compute the time it takes to complete jobs

import java.io.*;
import java.util.Scanner;

public class Simulation{
    public static void main(String[] args) throws IOException{
	Scanner in = null;
	String numJobs = null;
	String line = null;
	Queue storage = new Queue();
	Queue backupStorage = new Queue(); 
	Queue finishedJobs = new Queue(); 
	Queue[] processors = null; //array of Queues
	Job j = null;
	int work; //number of jobs 
	int t = 0; //time

    // check number of command line arguments
    if(args.length < 1){
        System.out.println("Usage: Simulation input file");
        System.exit(1);
    }

    //opens files for printing
    in = new Scanner(new File(args[0]));
    PrintWriter trace = new PrintWriter(new FileWriter(args[0] + ".trc"));
    PrintWriter report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
    numJobs = in.nextLine();
    work = Integer.parseInt(numJobs);

    //copies jobs into backup queue
    while(in.hasNext()){
	j = getJob(in);
	backupStorage.enqueue(j);
    }
    
    //prints text to files
    trace.println("Trace file: " + (args[0] + ".trc"));
    trace.println(work + " Jobs:");
    trace.println(backupStorage.toString());
    trace.println();

    report.println("Report file: " + (args[0] + ".rpt"));
    report.println(work + "Jobs:");
    report.println(backupStorage.toString());
    report.println();
    report.print("*****************************");

    //run simulation with n processors for n=1 to n=m-1
    for(int n = 1; n < work; n++){
	int completeWait = 0;
	int maxWait = 0;
	double avgWait = 0.00;

	for(int i = 0; i < backupStorage.length() + 1; i++){
	    j = (Job)backupStorage.dequeue();
	    j.resetFinishTime();
	    storage.enqueue(j);
	    backupStorage.enqueue(j);
	}
	//organizes processor queues
	int numProcessors = n;
	processors = new Queue[n+2];
	processors[0] = storage;
	processors[n+1] = finishedJobs;
	for(int i = 1; i < n+1; i++){
	    processors[i] = new Queue();
	}

	trace.println("*****************************");

	//distiguish between single and multiple processors
	if(numProcessors != 1){
	    trace.println(numProcessors + " processors:");
	}else{ 
	    trace.println(numProcessors + " processor:");
	}

	trace.println("*****************************");

	trace.println("time = " + t);
	trace.println("0: " + storage.toString());

	for(int i = 1; i < numProcessors+1; i++){
	    trace.println(i + ": " + processors[i]);
	}

	//if there are unfinished jobs...
	while(finishedJobs.length() != work){
	    int finalTime = Integer.MAX_VALUE;
	    int finalIndex = 1;
	    int c = 1;
	    int length;
	    int finalLength;
	    Job compare = null;
	    
	    if(!storage.isEmpty()){
		compare = (Job)storage.peek();
		finalTime = compare.getArrival();
		finalIndex = 0;
	    }

	    //calculate time
	    for(int i = 1; i < numProcessors+1; i++){
		if(processors[i].length() != 0){
		    compare = (Job)processors[i].peek();
		    c = compare.getFinish();   
		}
		if(c == 1){
		}else if(c < finalTime){
		    finalTime = c;
		    finalIndex = i;
		}
		t = finalTime;
	    }

	    if(finalIndex == 0){
		int tempIndex = 1;
		finalLength = processors[tempIndex].length();
		for(int i = 1; i < numProcessors+1; i++){
		    length = processors[i].length();
		    if(length < finalLength){
			finalLength = length;
			tempIndex = i;
		    }
		}

		compare = (Job)storage.dequeue();
		processors[tempIndex].enqueue(compare);
		if(processors[tempIndex].length() == 1){
		    compare = (Job)processors[tempIndex].peek();
		    compare.computeFinishTime(t);
		}
	    }else{
		compare = (Job)processors[finalIndex].dequeue();
		finishedJobs.enqueue(compare);
		int tempWait = compare.getWaitTime();
		if(tempWait > maxWait){
		    maxWait = tempWait;
		}
		completeWait = completeWait + tempWait;

		if(processors[finalIndex].length() >= 1){
		    compare = (Job)processors[finalIndex].peek();
		    compare.computeFinishTime(t);
		}
	    }

	    trace.println();
	    trace.println("time: " + t);
	    trace.println("0: " + storage.toString());
	    for(int i = 1; i < numProcessors+1; i++){
		trace.println(i + ": " + processors[i]);
	    }
	}

	//calculate wait times
	avgWait = ((double)completeWait/work);
	avgWait = (double)Math.round(avgWait*100)/100;
	trace.println();
	report.println();

	if(numProcessors == 1){
	    report.print(numProcessors + "processors: completeWait = " + completeWait + ", maxWait = " + maxWait + "avgWait = " + avgWait);
	}else{
	    report.print(numProcessors + "processor: completeWait = " + completeWait + ", maxWait = " + maxWait + "avgWait = " + avgWait);
	}

	t = 0;
	finishedJobs.dequeueAll();
    }
	in.close();
	report.close();
	trace.close();
    }

public static Job getJob(Scanner in) {
    String[] s = in.nextLine().split(" ");
    int a = Integer.parseInt(s[0]);
    int d = Integer.parseInt(s[1]);
    return new Job(a, d);
 }
}

