package CS145MP2RodriguezJules;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MyConnection {
	Socket s;
	BufferedReader in;
	PrintWriter out;

	public MyConnection(Socket s) {
		try {
			this.s = s;
			this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public boolean sendMessage(String msg) {
		try {
			this.out.println(msg);
			this.out.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Something bad happend!");
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public String getMessage() {
		try {
			String msg = "";
			msg = this.in.readLine();
			return msg;
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean sendName(String name) {
		try {
			this.out.println("Server: Hello, 1" + name);
			this.out.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Something bad happend!");
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendHello(String name) {
		try {
			this.out.println("Server: Hello, " + name);
			this.out.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Something bad happend!");
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}
	
	
}