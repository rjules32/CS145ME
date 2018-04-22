
package CS145ME7RodriguezJules;/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
					
					String[] header = arrMsg.get(0).split(" ");
					String requestedFile = System.getProperty("user.dir") + header[1];
					
					System.out.println(requestedFile);
										
					response = respond(requestedFile);		
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
	
	
	private static String respond(String file) throws IOException{

		File fp = new File(file);			
		String content = "";
		String fileContent = "";
		
		if (fp.isFile()) {
			InputStream is = new FileInputStream(fp);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			
			while (content != null) {
				content = in.readLine();
				if(content == null) break;
				fileContent += content;
			}			
			in.close();	
			
			
			return "HTTP/1.1 200 OK\n" + 
					"Connection close\n" + 
					"Date: " + new Date().toString() + 
					"Server: Apache/1.3.0 (Unix)\n" + 
					"Last-Modified: Mon, 22 Jun 1998 …...\n" + 
					"Content-Length: 49\n" + 
					"Content-Type: text/html\n" + 
					"\n" + 
					fileContent;
		}

		InputStream is = new FileInputStream(new File(System.getProperty("user.dir") + "/error.html"));
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		
		while (content != null) {
			content = in.readLine();
			System.out.println(content);
			if(content == null) break;
			fileContent += content;
			System.out.println(fileContent);
		}			
		in.close();	
		
		
		return "HTTP/1.1 404 Not Found\n" + 
				"Connection close\n" + 
				"Date: " + new Date().toString() + 
				"Server: Apache/1.3.0 (Unix)\n" + 
				"Last-Modified: Mon, 22 Jun 1998 …...\n" + 
				"Content-Length: 49\n" + 
				"Content-Type: text/html\n" + 
				"\n" + fileContent;
	}
	

}