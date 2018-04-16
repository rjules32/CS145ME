
package CS145ME6RodriguezJules;/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyServer {
	public static void main(String args[]) {
		int port = 8888;
						
		try {
			System.out.println("S: Starting server...");
			
			ServerSocket ssocket = new ServerSocket(port);
			Socket socket;
			MyConnection conn;
			System.out.println("S: Waiting for connections...");
			while(true){
				socket = ssocket.accept();			
				conn = new MyConnection(socket);			
				String msg = "", response = "";	
				List<String> arrMsg = new ArrayList<String>();
				int counter = 0;
				while(counter < 1) {
					msg = conn.getMessage(); 
					if(msg.isEmpty()) counter++;
					arrMsg.add(msg);
					System.out.println(msg);
				}
				
				
				if (arrMsg.get(0).contains("GET ")){
					response = respond(arrMsg.get(0));
					System.out.println(response);
					conn.sendMessage(response);
					break;
				}
			}
			socket.close(); 		
			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	private static String respond(String msg){
		if (msg.contains("index.html")){
			return "HTTP/1.1 200 OK\n" + 
					"Connection close\n" + 
					"Date: " + new Date().toString() + 
					"Server: Apache/1.3.0 (Unix)\n" + 
					"Last-Modified: Mon, 22 Jun 1998 …...\n" + 
					"Content-Length: 49\n" + 
					"Content-Type: text/html\n" + 
					"\n" + 
					"<html>\n" + 
					"<body>\n" + 
					"<h1> hello world! </h1>\n" + 
					"<body>\n" + 
					"</html>";
		}
		return null;
	}
}