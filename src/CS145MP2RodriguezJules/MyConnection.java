package CS145MP2RodriguezJules;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MyConnection {
	Socket s;
	BufferedReader in;
	PrintWriter out;
	BufferedInputStream bis = null;
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
			byte[] arrByte = new byte[(int) fp.length()];
			bis = new BufferedInputStream(new FileInputStream(fp));
			bis.read(arrByte, 0, arrByte.length);
			os = s.getOutputStream();

			System.out.println("Sending " + file + "(" + arrByte.length + " bytes)");

			os.write(arrByte, 0, arrByte.length);
			os.flush();
			System.out.println("Done.");
			
			return true;
		} catch (Exception e) {
			System.out.println("Something bad happend!");
			System.out.println(e);
			e.printStackTrace();
			return false;

		}finally {
			if (bis != null) bis.close();
	       	if (os != null) os.close();

		}

	}

}