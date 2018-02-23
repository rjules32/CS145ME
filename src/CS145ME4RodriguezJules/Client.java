/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/
package CS145ME4RodriguezJules;
import java.net.Socket;
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
			int inputCategory;
			
			while(true){
				socket = new Socket(ipAddress, port);
				conn = new MyConnection(socket);
				userInput = getUserInput();
				inputCategory = validateInput(userInput);
				switch(inputCategory){
					case 0:
						conn.sendMessage(userInput); 
						break;
					case 1:
						conn.sendMessage("TIME");
						break;
					case 2:
						conn.sendMessage("JOKE TIME");
						break;
					case 3:
						conn.sendMessage("QUIT");
						break;
					case -1:
						conn.sendMessage("INVALID");
						break;
					default: 
						conn.sendMessage("INVALID");
						break;
						
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
	
	private static String getResponse(MyConnection conn){
		return conn.getMessage();
	}
	
	private static int validateInput(String msg){
		
		if(msg.contains("MY NAME IS ")){
			return 0;
		}else{
			switch(msg){
				case "TIME":
					return 1;			
				case "JOKE TIME":
					return 2;
				case "QUIT":
					return 3;
				default:
					return -1;
			}
		}
	}
	
	
}