/*
 * Coded by: Jules Rodriguez
 * Subject/Section: CS 145 GRAD
 * 
 * 
*/

import java.io.*;
import java.net.*;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MyServer {
	public static void main(String args[]) {
		int port = 8888;
		String currentPath = System.getProperty("user.dir");
						
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
				
				msg = conn.getMessage(); 
				
				if(msg.equals("JOKE TIME")){
					String[] jokeArr = {
						"Tatay: Anak! Ano ibig sabihin ng F dito sa card mo?!?!"
						,"Anak: (Nag-iisip) Tatay, Fasado po ibig sabihin niyan."
						,"Tatay: Ahhhh... akala ko Ferfect."
						};
					for(int i = 0; i < jokeArr.length; i++){
						response = jokeArr[i];
						conn.sendMessage(response);
					}
					conn.sendMessage("end");
				}else if(msg.equals("LS")){
					String path = currentPath;
					File directory = new File(path);
					String[] list = directory.list();
					for (int i = 0; i < list.length; i++) {
						File item = new File(path + "/" + list[i]);
						response = (item.isDirectory())? "Directory -- " + list[i] : "File -- " + list[i];
						conn.sendMessage(response);
					}					
					conn.sendMessage("end");
				}else if (msg.contains("GET ")){
					String file = msg.replace("GET ", "");
					File fp = new File(currentPath + "\\" + file);
					if (fp.isFile()) {
						if(file.substring(file.length() - 4, file.length()).equals(".txt")){
							
							InputStream is = new FileInputStream(fp);
							BufferedReader in = new BufferedReader(new InputStreamReader(is));
							
							conn.sendMessage("provide your pwd");
							String clientPWD = conn.getMessage();
							
							File fout = new File(clientPWD + "\\" + file);
							PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fout)));
													
							String content = "";
							while (content != null) {
								content = in.readLine();
								if(content == null) break;
								System.out.println(content);
								out.println(content);
								out.flush();
							}			
							in.close();	
							out.close();
							response = "File saved in " + clientPWD;
						}else{
							response = "File type is not text (.txt)";
						}	
					} else {
						response = "This file does not exist.";
					}
					conn.sendMessage(response);
					conn.sendMessage("end");
				}else if (msg.equals("PWD")){
					conn.sendMessage("The current directory is -- " + currentPath);
					conn.sendMessage("end");
					
				}else if (msg.contains("CD ..")){
					int lastIndex = (currentPath.lastIndexOf("/") == -1)? currentPath.lastIndexOf("\\") : currentPath.lastIndexOf("/");
					if(lastIndex == -1){
						currentPath = currentPath + "\\";
						response = "There is no more directory beyond " + currentPath;
						conn.sendMessage(response);
						conn.sendMessage("end");
					}else{		
						currentPath = currentPath.replace(currentPath.substring(lastIndex, currentPath.length()),"");
						if(!(currentPath.contains("/") || currentPath.contains("\\"))) currentPath = currentPath + "\\";
						response = "The directory path is -- " + currentPath;
						conn.sendMessage(response);
						conn.sendMessage("end");	
					}					
					
				}else if (msg.contains("CD ")){
					msg = msg.replace("CD ", "");
					
					if(msg.indexOf("\"") == 0 && msg.lastIndexOf("\"") == msg.length() - 1){
						String requestedDirectory = msg.replace("\"", "");
						String path = (currentPath.indexOf("\\") == currentPath.length() - 1)? 
								currentPath + requestedDirectory : currentPath + "\\" + requestedDirectory;
						File dir = new File(path);
						if(dir.isDirectory()){
							currentPath = path;
							response = "The directory path is -- " + currentPath;
						}else{
							if(dir.isFile()){
								response = requestedDirectory + " is a file, not a directory.";
							}else{
								response = requestedDirectory + " is non-existent";
							}
						}
					}else{
						response = "Please envelop the directory name with quotation marks (\").";
					}
					conn.sendMessage(response);
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
					
					Date date = new Date();
					time = date.toString();
					
					return "The time now is " + time;
				case "QUIT":
					return "Goodbye";
				default:
					return "I can't understand what you're saying";
					
			}
		}	
	}
}