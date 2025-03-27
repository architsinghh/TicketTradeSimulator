package architsi_CSCI201_Assignment2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.JsonParseException;
import com.opencsv.CSVReader;


public class main {
	
	public static int balance;
	
	public static void main(String [] args) {
		
		
		
		BufferedReader inputBR = new BufferedReader(new InputStreamReader(System.in));
		
		String jsonFileName = null;
		String json = null;
		Gson gson = new Gson();
		Exhibit[] exhibits = null;
		
		boolean jsonIsValid = false;
		while(!jsonIsValid){
			try {
				System.out.println("What is the name of the exhibits file?");
				jsonFileName = inputBR.readLine();
				json = Files.readString(Paths.get(jsonFileName));
				String exhibitsFromFile = json.substring(json.indexOf("["), json.indexOf("]") + 1);
				exhibits = gson.fromJson(exhibitsFromFile, Exhibit[].class);
				System.out.println("This file has been properly read");
				jsonIsValid = true;
			
			}
			catch(JsonParseException e) {
				System.out.println("The file " + jsonFileName + " is not formatted properly");
			}
			catch(IOException e){
				System.out.println("The file " + jsonFileName + " could not be found.");
				System.out.println();
			}
		}
		
		
		
		
		
		BufferedReader inputBR2 = new BufferedReader(new InputStreamReader(System.in)); //this and below is for csv 
		BufferedReader inputBR3 = new BufferedReader(new InputStreamReader(System.in)); //this is for initial balance input
		
		
		String csvFileName = null;
		int initialBalance = 0;
	
		
		boolean csvIsValid = false;
		while(!csvIsValid) {
			try {
				System.out.println("What is the name of the schedule file?");
				csvFileName = inputBR2.readLine();
				Path filePath = Paths.get(csvFileName);
				List<String[]> fileInfo = readAllLines(filePath);
				System.out.println("The schedule file has been properly read.");
				
				System.out.println("What is the initial balance?");
				String StrInitalBalance = inputBR3.readLine();
				initialBalance = Integer.parseInt(StrInitalBalance);
				
				balance = initialBalance;
				
				
				
				List<Task> tasks = new ArrayList<>();
				for(int i = 0; i < fileInfo.size(); i++) {
					String[] row = fileInfo.get(i);
					int time = Integer.parseInt(row[0]);
					String name = row[1];
					int ticketQty = Integer.parseInt(row[2]);
					int price = Integer.parseInt(row[3]);
					
					Task task = new Task(time, name, ticketQty, price);
					tasks.add(task);
				}
				

				
				Map<String, AgentSemaphore> agentSemaphore = new HashMap<>();
				
				for (int i = 0; i < exhibits.length; i++) {
					Exhibit exhibit = exhibits[i];
					int numberOfAgents = Integer.parseInt(exhibit.getAgents());
					
				    agentSemaphore.put(exhibit.getName(), new AgentSemaphore(numberOfAgents));
				}
				
				long start = System.currentTimeMillis();
				
				ExecutorService executor = Executors.newCachedThreadPool();
				List<Trade> transactions = new ArrayList<>();
				
				System.out.println();
				System.out.println("Starting execution of program...");
				System.out.println("Initial Balance: " + initialBalance);
				
				for(int i = 0; i< tasks.size(); i++) {
					Task task = tasks.get(i);
					AgentSemaphore exhibitSem = agentSemaphore.get(task.getName());
					Trade trade = new Trade(start, task, exhibitSem);
					executor.execute(trade);
					transactions.add(trade);
				}
				
				executor.shutdown();
				while(!executor.isTerminated()) {
					Thread.yield();
				}
				
				if(Trade.rejections == 0) {
					System.out.println("All exhibit ticket trades completed!");
				}
				else if(Trade.rejections > 0) {
					System.out.println("All exhibit ticket trades completed, except " + Trade.rejections + " trades.");
				}
				
				System.out.print("Current Balance after the execution of the schedule: " + balance);
				
				csvIsValid = true;
				
				
				
			}
			catch(Exception e){
				System.out.println("The file " + csvFileName + " could not be found.");
				System.out.println();
			}
			
			
		}
	
		
	}
	
	
	public static List<String[]> readAllLines(Path filePath) throws Exception { //from professor's resource on piazza
	    try (Reader reader = Files.newBufferedReader(filePath)) {
	        try (CSVReader csvReader = new CSVReader(reader)) {
	            return csvReader.readAll();
	        }
	    }
	}
	
	
	
}
		
	
