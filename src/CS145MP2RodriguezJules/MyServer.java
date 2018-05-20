
package CS145MP2RodriguezJules;

/*
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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyServer {
	public static void main(String args[]) throws IOException {
		int port = 8888;
		System.out.println("S: Starting server...");

		ServerSocket ssocket = new ServerSocket(port);
		Socket socket;
		MyConnection conn;

		System.out.println("S: Waiting for connections...");
		while (true) {
			socket = ssocket.accept();
			try {
				conn = new MyConnection(socket);

				// Get request
				List<String> request = getHttpRequest(conn);

				// Send Response
				sendResponse(conn, request);

			} catch (Exception e) {
				System.out.println("Something bad happened!");
				System.out.println(e);
				e.printStackTrace();

			} finally {
				socket.close();
				System.out.println("--Socket Closed--");
			}
		}

	}
	
	

	private static List<String> getHttpRequest(MyConnection conn) {
		String msg = "";
		List<String> arrMsg = new ArrayList<String>();
		int counter = 0;
		while (counter < 1) {
			msg = conn.getMessage();
			if (msg.isEmpty()) counter++;
			arrMsg.add(msg);
			System.out.println(msg);
		}

		return arrMsg;
	}

	private static void sendResponse(MyConnection conn, List<String> request) throws IOException {
		String responseHeader = "";
		String requestLine = request.get(0);
		
		if (requestLine.contains("GET ")) {

			String[] requestHeader = requestLine.split(" ");
			String requestedFile = System.getProperty("user.dir") + requestHeader[1];
		//	System.out.println(requestedFile);

			File fp = new File(requestedFile);
			
			if (fp.isFile()) {
				// Send Response Header
				responseHeader = getResponseHeader(200, requestedFile);
				conn.sendMessage(responseHeader);
				System.out.println(responseHeader);

				// Send File			
				conn.sendByte(requestedFile);
				
			} else {
				//Send Response Header
				conn.sendMessage(getResponseHeader(404, requestedFile));
				
				// Send File
				conn.sendMessage(getFile(requestedFile));
				
			}
		}

	}

	private static String getResponseHeader(int status, String file) {
		String header = "", contentType = "";
		String fileExt = file.substring(file.lastIndexOf(".") + 1);
		switch(fileExt) {
			case "jpeg":
				contentType = "image/jpeg";
				break;
			case "jpg":
				contentType = "image/jpeg";
				break;
			case "png":
				contentType = "image/png";
				break;
			case "gif":
				contentType = "image/gif";
				break;
			case "css":
				contentType = "text/css";
				break;
			case "html":
				contentType = "text/html";
				break;
			default:
				contentType = "text/html";
		}
		File fp = new File(file);
		if(status == 200) {
			header = "HTTP/1.1 200 OK \n" + 
					"Connection: close \n" + 
					"Date: " + new Date().toString() + " \n" +
					"Content-Type: " + contentType + " \n" + 
					"Content-Length: " + fp.length() + " \n" + 
					"\r\n";
		} else {

			File errFp = new File(System.getProperty("user.dir") + "/error.html");
			header = "HTTP/1.1 404 Not Found \n" + 
					"Connection: close \n" + 
					"Date: " + new Date().toString() + " \n" +
					"Content-Length: " + errFp.length() + "\n" + 
					"Content-Type: text/html \n" + 
					"\r\n";			
		}
		
		return header;
	}

	private static String getFile(String file) throws IOException {

		File fp = new File(file);
		String content = "";
		String fileContent = "";

		if (fp.isFile()) {
			InputStream is = new FileInputStream(fp);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));

			while (content != null) {
				content = in.readLine();
				if (content == null)
					break;
				fileContent += content;
			}
			in.close();
			System.out.println(fileContent);

			return fileContent;
		}

		return fileContent;
	}

}