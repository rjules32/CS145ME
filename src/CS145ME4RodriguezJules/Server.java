/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/
package CS145ME4RodriguezJules;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Server {
	public static void main(String args[]) {
		String[] jokeArr = {"Tatay: Anak! Ano ibig sabihin ng F dito sa card mo?!?!","Anak: (Nag-iisip) Tatay, Fasado po ibig sabihin niyan.","Tatay: Ahhhh... akala ko Ferfect."};
		
		try {
			System.out.println("S: Starting server...");
			
			ServerSocket ssocket = new ServerSocket(8888);
			Socket socket;
			MyConnection conn;
			System.out.println("S: Waiting for connections...");
			while(true){
				socket = ssocket.accept();			
				conn = new MyConnection(socket);			
				String msg = "", response = "";	
				
				msg = conn.getMessage(); 
				
				if(msg.equals("JOKE TIME")){
					for(int i = 0; i < jokeArr.length; i++){
						response = jokeArr[i];
						System.out.println(response);
						conn.sendMessage(response);
					}
					conn.sendMessage("end");
				}else{
					response = respond(msg);
					System.out.println(response);
					conn.sendMessage(response);
					conn.sendMessage("end");
				}
				
				if(response.equals("Goodbye")) break;
			}
			socket.close(); 		
			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	private static String respond(String msg){
		if (msg.contains("MY NAME IS ")){
			return "Hello, " + msg.replace("MY NAME IS ","");
		}else{
			switch(msg){
				case "TIME":
					String time;
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMMM d HH:mm:ss yyyy");
					LocalDateTime now = LocalDateTime.now();
					
					time = dtf.format(now);
					return "The time now is " + time;
				case "QUIT":
					return "Goodbye";
				case "INVALID":
					return "I can't understand what you're saying";
				default:
					return "I can't understand what you're saying";
					
			}
		}	
	}
}