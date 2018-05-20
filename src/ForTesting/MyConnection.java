package ForTesting;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MyConnection {
	Socket s;
	BufferedReader in;
	PrintWriter out;
	FileInputStream fis = null;
	InputStream is = null;
	OutputStream os = null;
	PrintStream ps = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;

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
	

	public boolean sendByte(String file) throws IOException {
		try {
			File fp = new File(file);
			byte[] arrByte = new byte[1];
//		    dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
//		    dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));

			
			fis = new FileInputStream(fp);
			os = s.getOutputStream();
			//ps = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
			//ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream()) ;

			
			int count;
			while((count = fis.read(arrByte)) > 0 ) {
				os.write(arrByte, 0, count);		
//				ps.write(arrByte, 0, count);		
//				dos.write(arrByte, 0, count);		
			}
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Something bad happend!");
			System.out.println(e);
			e.printStackTrace();
			return false;

		}finally {
			if (is != null) is.close();
			if (fis != null) fis.close();
	       	if (os != null) os.close();
	       	if (dis != null) dis.close();
	       	if (dos != null) dos.close();

		}

	}

}