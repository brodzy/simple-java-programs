/******************************************************************************
Program Name: scheduling_brodzinski.java
Programmer: Brandon Brodzinski
Class: CS 4345 / Spring 2022
Project: Assignment 3 / CPU Scheduler
*******************************************************************************/
import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class CPU_Scheduler {
	//Checks for duplicate process ids
	 public static boolean checkDuplicate(ArrayList<Process> procList, int id) {
         for(int i = 0;i < procList.size(); i++) {
                 if(procList.get(i).getPid() == id)
                         return true;
         }
         return false;
	 }
	 
	 //Checks id to be in the correct range
	 public static boolean checkId(int num) {
		 if(num <= 10 && num >= 0) {
			 return true;
		 }
		 return false;
	 }
	 
	//Checks priority to be in the correct range
	 public static boolean checkPriority(int num) {
		 if(num <= 10 && num >= 1) {
			 return true;
		 }
		 return false;
	 }
	 
	//Checks burst to be in the correct range
	 public static boolean checkBurst(int num) {
		 if(num <= 100 && num >= 20) {
			 return true;
		 }
		 return false;
	 }
	 
	 //Non-preemptive SJF Algorithm
	 public static double shortestJobFirst(ArrayList<Process> procList, HashMap<Double, String> avgWaitTime){
	        ArrayList<Process> sjfList = new ArrayList<>();

	        procList.forEach(i -> sjfList.add(i));

	        sjfList.sort((a, b) -> a.getBurstLength() - b.getBurstLength());

	        for(int i = 0; i < sjfList.size(); i++){
	            for(int j = i+1; j < sjfList.size(); j++){
	            	sjfList.get(j).wait(sjfList.get(i).getBurstLength());
	            }
	        }
	        
	        int totalWait = 0;
	        for(Process var : sjfList){
	        	totalWait +=var.getWaitTime();
	        }

	        //Display output
	        System.out.printf("\n%-23s | %-15s | %-15s | %-15s | %-15s","Scheduling Algorithm","Process ID", "Priority", "Burst-length", "Total Waiting Time");
	        for(int i = 0; i < procList.size(); i++){
	            System.out.printf("\n%-23s | %-15d | %-15d | %-15d | %-15d", "Non-preemptive SJF", procList.get(i).getPid(), procList.get(i).getPriority(), procList.get(i).getBurstLength(), procList.get(i).getWaitTime());
	        }
	        
	        //Add to map for displaying average time results
	        double sjfTime = ((double)totalWait) / sjfList.size();
	        avgWaitTime.put(sjfTime, "Non-preemptive SJF");
	        
	        System.out.println("\n\nAverage wait time for Non-preemptive SJF is: " + sjfTime);
	        return sjfTime;
	    }   
	 
	//Non-preemptive Priority Algorithm
	 public static double priority(ArrayList<Process> procList, HashMap<Double, String> avgWaitTime){
	        ArrayList<Process> priorityList = new ArrayList<>();

	        procList.forEach(i -> priorityList.add(i));

	        priorityList.sort((a, b) -> a.getPriority() - b.getPriority());

	        for(Process var : priorityList){
	        	var.waitTime = 0;
	        }

	        for(int i = 0; i < priorityList.size(); i++){
	            for(int j = i+1; j < priorityList.size(); j++){
	            	priorityList.get(j).wait(priorityList.get(i).getBurstLength());
	            }
	        }
	        
	        int totalWait = 0;
	        for(Process var : priorityList){
	        	totalWait +=var.getWaitTime();
	        }

	        //Display output
	        System.out.printf("\n%-23s | %-15s | %-15s | %-15s | %-15s","Scheduling Algorithm","Process ID", "Priority", "Burst-length", "Total Waiting Time");
	        for(int i = 0; i < procList.size(); i++){
	        	System.out.printf("\n%-23s | %-15d | %-15d | %-15d | %-15d", "Non-preemptive Priority", procList.get(i).getPid(), procList.get(i).getPriority(), procList.get(i).getBurstLength(), procList.get(i).getWaitTime());
	        }
	        
	        //Add to map for displaying average time results
	        double pTime = ((double)totalWait) / priorityList.size();
	        avgWaitTime.put(pTime, "Non-preemptive Priority");
	        
	        System.out.println("\n\nAverage wait time for Non-preemptive Priority is: " + pTime);
	        return pTime;
	    }
	 
	//Non-preemptive Round Robin Algorithm
	 public static double roundRobin(ArrayList<Process> procList, HashMap<Double, String> avgWaitTime, int quantum){
	        procList.forEach(i -> i.waitTime = 0);

	        ArrayList<Process> rrList = new ArrayList<>();
	        ArrayList<Integer> intList = new ArrayList<>();

	        procList.forEach(i -> rrList.add(i));
	        procList.forEach(i -> intList.add(i.burstLength));

	        boolean run = true;

	        int wait = quantum;
	        if(rrList.get(0).burstLength < quantum){
	            wait = rrList.get(0).burstLength;
	        }

	        int process = 0;

	        while(run){
	            for(int i = 0; i < rrList.size(); i++){
	                if(i == process){
	                    rrList.get(i).burstLength -= wait;
	                }
	                else{
	                    if(rrList.get(i).burstLength > 0){
	                        rrList.get(i).wait(wait);
	                    }
	                }
	            }

	            if(rrList.size() > 0){
	                if(process >= rrList.size()-1){
	                    process = 0;
	                }
	                else{
	                    process++;
	                }

	                if(rrList.get(process).burstLength >= quantum){
	                    wait = quantum;
	                }
	                else {
	                    wait = rrList.get(process).burstLength;
	                }

	            }

	            int temp = 0;
	            for(int i = 0; i < rrList.size(); i++){
	                temp += rrList.get(i).burstLength;
	            }

	            if(temp == 0)
	                run = false;
	        }

	        procList.forEach(i -> rrList.add(i));

	        int totalWait = 0;
	        for(Process var : rrList){
	        	totalWait +=var.getWaitTime();
	        	}

	        for(int i = 0; i < intList.size(); i++){
	        	procList.get(i).burstLength = intList.get(i);
	        	}
	        
	        //Display output
	        System.out.printf("\n%-23s | %-15s | %-15s | %-15s | %-15s","Scheduling Algorithm","Process ID", "Priority", "Burst-length", "Total Waiting Time");
	        for(int i = 0; i < procList.size(); i++){
	        	System.out.printf("\n%-23s | %-15d | %-15d | %-15d | %-15d", "Round Robin", procList.get(i).getPid(), procList.get(i).getPriority(), procList.get(i).getBurstLength(), procList.get(i).getWaitTime());
	        }
	        
	        //Add to map for displaying avg time result
	        double rrTime = ((double)totalWait) / rrList.size();
	        avgWaitTime.put(rrTime, "Round Robin");
	        
	        System.out.println("\n\nAverage wait time for Round Robin Scheduling is: " + rrTime);
	        return rrTime;
	    }
         
	public static void main(String[] args) {
		//Vars and Lists
		int id, burst, priority;
		int quantum = 25;
		ArrayList<Process> procList = new ArrayList<Process>();
		HashMap<Double, String> avgWaitTime = new HashMap<Double, String>();
		
        //Creating 5 processes for intial snapshot
        for(int i = 0; i < 5; i++) {
        	//Checks to make sure its a unique pid
        	 while(true) {
        		 id = ((int) (Math.random() * 10));
                 if(checkDuplicate(procList, id) == false)
                         break;
        	 }
            burst = ((int) (Math.random() * (100 - 20))) + 20;
            priority = ((int) (Math.random() * (10 - 1))) + 1;
            
            //Create new process if conditions are met
            procList.add(new Process(id, priority, burst));
        }
        
        //Sorting list by pid ascending order
        Collections.sort(procList);
  
        //Displaying initial snapshot
        System.out.printf("%-15s | %-15s | %-15s","Process ID","Priority","Burst-length");
        for(int i = 0; i < procList.size(); i++) {
        	System.out.printf("\n%-15s | %-15s | %-15s",procList.get(i).getPid(), procList.get(i).getPriority(), procList.get(i).getBurstLength());
        }
        System.out.println();
        
        //Allowing user input
        int userId, userPri, userBurst;
        Scanner scan = new Scanner(System.in);
        
        //Validates conditions no duplicates and valid range for pid
        System.out.print("\nPlease enter a process ID between 0 and 10: ");
        while(true) {
        	userId = scan.nextInt();
            if(checkDuplicate(procList, userId) == false && checkId(userId) == true) {
                    break;
            }
            System.out.print("Please enter a unique process ID with a range between 0 and 10! ");
            
   	 	}
        
        //Validates valid range for priority
        System.out.print("Please enter a priority between 1 and 10: ");
        while(true) {
        	userPri = scan.nextInt();
            if(checkPriority(userPri) == true) {
                    break;
            }
            System.out.print("Please enter a valid number! ");
            
   	 	}
        
        //Validates valid range for burst length
        System.out.print("Please enter a burst length between 20 and 100: ");
        while(true) {
        	userBurst = scan.nextInt();
            if(checkBurst(userBurst) == true) {
                    break;
            }
            System.out.print("Please enter a valid number! ");
            
   	 	}
        
        //Adds new process and sorts list by pid ascending order
        procList.add(new Process(userId, userPri, userBurst));
        Collections.sort(procList);
        scan.close();
        
        //Displaying updated snap shot
        System.out.printf("\n%-15s | %-15s | %-15s","Process ID","Priority","Burst-length");
        for(int i = 0; i < procList.size(); i++) {
        	System.out.printf("\n%-15s | %-15s | %-15s",procList.get(i).getPid(), procList.get(i).getPriority(), procList.get(i).getBurstLength());
        }
        System.out.println("\n");
        
        //Scheduling Algorithms
        
        //Non-preemptive SJF
        shortestJobFirst(procList, avgWaitTime);
        
        //Non-preemptive Priority
        priority(procList, avgWaitTime);
        
        //Round Robin
        roundRobin(procList, avgWaitTime, quantum);
		
        //Displays sorted average wait times
        TreeMap<Double, String> map = new TreeMap<Double, String>(avgWaitTime);
        System.out.printf("\n%-23s | %-15s ","Scheduling Algorithm","Average Waiting Time");
        for (Map.Entry<Double, String> pair: map.entrySet()){
        	System.out.printf("\n%-23s | %-15s", pair.getValue(), pair.getKey());
        }
        
	}

}
