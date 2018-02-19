package CS145ME4RodriguezJules;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String args[]) {
		try {
			
			ServerSocket ssocket = new ServerSocket(8888);
			
			Socket socket = ssocket.accept();
			
			MyConnection conn = new MyConnection(socket);
						
			String msg = "";			
			msg = conn.getMessage(); 
			System.out.println(msg);

			MyConnection conn1 = new MyConnection(socket);
			conn1.sendHello(conn1.getMessage());
			
			socket.close(); 
			
			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}