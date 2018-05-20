
package ForTesting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import CS145MP2RodriguezJules.MyConnection;

public class MyServer {
	public static void main(String args[]) throws IOException {
		ServerSocket ssocket = new ServerSocket(8888);
		Socket socket;
		MyConnection conn;
		socket = ssocket.accept();

		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));


		conn = new MyConnection(socket);
		String msg = "";
		int counter = 0;
		while (counter < 1) {
			msg = conn.getMessage();
			if (msg.isEmpty()) counter++;
			System.out.println(msg);
		}
		
		// File fp = new File(System.getProperty("user.dir") + "/res/H.jpeg");
		File fp = new File(System.getProperty("user.dir") + "/index.html");
		out.print("HTTP/1.1 200 OK \n");
		out.print("Connection: close \n");
		out.print("Content-Type: text/html \n");
		out.print("Content-Length: " + fp.length() + "\n");
		out.print("\r\n");
		out.flush();

		byte[] arrByte = new byte[1];
		FileInputStream fis = new FileInputStream(fp);
		OutputStream os = socket.getOutputStream();

		int count;
		while ((count = fis.read(arrByte)) != -1) {
			os.write(arrByte, 0, count);
		}
		

		fis.close();
		os.close();
		socket.close();
		
		
		
		
		String msg2 = "";

		socket = ssocket.accept();
		conn = new MyConnection(socket);
		int counter2 = 0;
		while (counter2 < 1) {
			msg2 = conn.getMessage();
			if (msg2.isEmpty()) counter2++;
			System.out.println(msg2);
		}
		
		File fp2 = new File(System.getProperty("user.dir") + "/res/H.jpeg");
		out.print("HTTP/1.1 200 OK \n");
		out.print("Connection: close \n");
		out.print("Content-Type: image/jpeg \n");
		out.print("Content-Length: " + fp2.length() + "\n");
		out.print("\r\n");
		out.flush();

		byte[] H = new byte[1];
		FileInputStream fis2 = new FileInputStream(fp2);
		OutputStream os2 = socket.getOutputStream();

		int count2;
		while ((count2 = fis2.read(arrByte)) > 0) {
			os2.write(H, 0, count2);
		}
		os.flush();	
		
		
		
		fis2.close();
		os2.close();
		socket.close();

	}
}