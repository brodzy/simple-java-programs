import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Multithread implements Runnable{
	//Vars
	private int threadNumber;
	List<Integer> row;
	
	//Constructors
	public Multithread(int threadNumber, List<Integer> row) {
		this.threadNumber = threadNumber;
		this.row = row;
	}
	
	@Override
	public void run () {
		System.out.println("This thread has started: " + threadNumber);
		Collections.sort(row);
		System.out.println("Sorted: " + row);
		System.out.println("This thread has ended: " + threadNumber + "\n");
		
	}
	
	//Extra Credit
	public static void sortedOutput(List<Multithread> thread, String name) throws IOException {
		PrintWriter outputFile = new PrintWriter(name.substring(0, name.lastIndexOf('.')) + "-sorted.txt");
		for(int i = 0; i < thread.size(); i++) {
			String str = Arrays.toString(thread.get(i).row.toArray()).replace('[', ' ').replace(']', ' ').replace(',', ' ').trim();
			outputFile.println(str);
		}
		outputFile.close();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			//Scanning file and initializing thread objects
			String fileName = args[0];
			File file = new File(args[0]);
			Scanner sc = new Scanner(file);
			List<Multithread> threads = new ArrayList <Multithread>();
			int count = 0;
			
			//Adding number from each row to list while keeping up with count of each row to determine amount of threads
			try {
			
				while(sc.hasNextLine()) {
					List<Integer> num = new ArrayList <Integer>();
					count++;
					String line = sc.nextLine();
					String[] integersInString = line.split("\\s+");
				
					for (int i = 0; i < integersInString.length; i++) {
						num.add(Integer.parseInt(integersInString[i]));
					}
				
					Multithread sortRow = new Multithread(count, num);
					threads.add(sortRow);
				}
				sc.close();
		    
				//Multi-threading
				for(int i =0; i < threads.size(); i++) {
					Thread worker = new Thread(threads.get(i));
					worker.start();
					worker.join();
				}
				System.out.println("\nRow-major sorting complete.");
				sortedOutput(threads, fileName);
				System.out.println("\nFile writing complete.");
			}
			catch(Exception e){
				System.out.println("Make sure the file has numbers only!");
			}
			
		}
		catch(Exception e){
			System.out.println("Please enter the correct file!");
		}
	}	
}
