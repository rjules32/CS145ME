
package CS145ME5RodriguezJules;/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) {
		String ipAddress = "127.0.0.1";
		int port = 8888;
		
		try {
			System.out.println("Client tries to connect to server...");
			Socket socket;
			System.out.println("Client connected!");
			MyConnection conn; 
			
			String userInput = "", response = "";
			
			while(true){
				socket = new Socket(ipAddress, port);
				conn = new MyConnection(socket);
				userInput = getUserInput();
				
				if(userInput.contains("MY NAME IS ")){
					conn.sendMessage(userInput); 
				}else if (userInput.contains("GET ")){
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
						default: 
							conn.sendMessage("INVALID");
							break;
							
					}
				}
				while(true){
					response = conn.getMessage();
					if (response.equals("end")) break;
					System.out.println("Server: " + response);
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