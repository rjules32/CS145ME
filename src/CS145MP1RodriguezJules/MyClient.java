package CS145MP1RodriguezJules;

/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
	public static void main(String args[]) {
		String ipAddress = "127.0.0.1";
		int port = 8888;
		
		try {
			System.out.println("Client tries to connect to server...");
			Socket socket;
			System.out.println("Client connected!");
			MyConnection conn; 
			
			String userInput = "", response = "";
			int inputCategory;
			
			while(true){
				socket = new Socket(ipAddress, port);
				conn = new MyConnection(socket);
				userInput = getUserInput();
				
				if(userInput.contains("MY NAME IS ")){
					conn.sendMessage(userInput); 
				}else if (userInput.contains("GET ")){
					conn.sendMessage(userInput);
				}else if (userInput.contains("CD ") && !userInput.equals("CD ..")){
					conn.sendMessage(userInput);
				}else {
					switch(userInput){
						case "TIME":
							conn.sendMessage(userInput);
							break;
						case "JOKE TIME":
							conn.sendMessage(userInput);
							break;
						case "QUIT":
							conn.sendMessage(userInput);
							break;
						case "LS":
							conn.sendMessage(userInput);
							break;							
						case "PWD":
							conn.sendMessage(userInput);
							break;
						case "CD ..":
							conn.sendMessage(userInput);
							break;
						default: 
							conn.sendMessage("INVALID");
							break;
							
					}
				}
				while(true){
					response = conn.getMessage();
					if(response.equals("provide your pwd")) conn.sendMessage(System.getProperty("user.dir"));
					if (response.equals("end")) break;
					if(!response.equals("provide your pwd")) System.out.println("Server: " + response);
				}
				if(userInput.equals("QUIT")) break;
			}
			
			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	
	private static String getUserInput(){		
		Scanner msgObj = new Scanner(System.in);
		
		System.out.print("Enter Message: ");		
		
		return msgObj.nextLine();
	}
	
	
}