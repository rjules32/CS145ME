package CS145ME4RodriguezJules;

import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) {
		Scanner nameObj = new Scanner(System.in);
		String msg = "", reply = "", name = "";
		try {
			System.out.println("Client tries to connect to server...");
			
			Socket socket = new Socket("127.0.0.1", 8888);
			
			System.out.println("Client connected!");
			
			System.out.print("Enter Message: MY NAME IS ");
			name = nameObj.nextLine();
			
			MyConnection conn = new MyConnection(socket);
			conn.sendName(name);  
			
			MyConnection connNew = new MyConnection(socket);
			
			String msg1 = "";			
			msg1 = connNew.getMessage(); 
			System.out.println(msg1);
			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}

	}
}