import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Process_TCPProbe {
		
	public static void main(String[] args) throws IOException, InterruptedException {
			try {
				//Reading file
				File file = new File(args[0]);
				BufferedReader brTCP = new BufferedReader(new FileReader(file));
				
				//Extracting port numbers from file arguments
			    String port1 = args[1].substring(9, 14);
			    String port2 = args[2].substring(9, 14);
			    String port3 = args[3].substring(9, 14);
			    
			    //Replacing all spaces with commas, deleting last line, and adding to new array list
				ArrayList<String> tcpList = new ArrayList<String>();
				String line;
				String previousLine = null;
				while ((line = brTCP.readLine()) != null) {
					line = line.replace(" ", ",");
				    if (previousLine != null) {
				    	tcpList.add(previousLine);
				    }
				    previousLine = line;
				}
				
				//Closing buffer reader and supplying user with message
				brTCP.close();
				System.out.println("Files sorted and outputted to .dat files!");
				
				//Write to supplied .dat files based on port number
				BufferedWriter bwPort1 = new BufferedWriter(new FileWriter(args[1]));
			   BufferedWriter bwPort2 = new BufferedWriter(new FileWriter(args[2]));
			   BufferedWriter bwPort3 = new BufferedWriter(new FileWriter(args[3]));
			    
			    //Loop through TCP list and if port numbers match write to the respective .dat file
			    for(int i = 0; i < tcpList.size(); i++)
			    {
			           if(tcpList.get(i).contains(port1))
			           {
			              bwPort1.write(tcpList.get(i));
			              bwPort1.newLine();
			             
			           }
			           else if(tcpList.get(i).contains(port2))
			           {
			              bwPort2.write(tcpList.get(i));
			              bwPort2.newLine();
			             
			           }
			           else if(tcpList.get(i).contains(port3))
			           {
			              bwPort3.write(tcpList.get(i));
			              bwPort3.newLine();
			             
			           }
			       }
			       
			     
			    //Flushing and closing all buffered writers
			    bwPort1.flush();
			    bwPort2.flush();
			    bwPort3.flush();
			    bwPort1.close();
			    bwPort2.close();
			    bwPort3.close();
			     
				
				
			}
			
			catch(Exception e){
				System.out.println("Please enter the correct file!");
			}
	}

}
