package CS145MP2RodriguezJules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MyConnection {
	Socket s;
	BufferedReader in;
	PrintWriter out;
	InputStream is = null;
	OutputStream os = null;

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
			this.out.print(msg);
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
	

	public boolean sendByte(String file) throws IOException {
		try {
			File fp = new File(file);
			byte[] arrByte = new byte[1];
			is = new FileInputStream(fp);
			os = s.getOutputStream();

			int count;
			while((count = is.read(arrByte)) > 0) {
				os.write(arrByte, 0, count);		
			}

			os.flush();	
			return true;
			
		} catch (Exception e) {
			System.out.println("Something bad happend!");
			System.out.println(e);
			e.printStackTrace();
			return false;

		}finally {
			if (is != null) is.close();
	       	if (os != null) os.close();

		}

	}

}