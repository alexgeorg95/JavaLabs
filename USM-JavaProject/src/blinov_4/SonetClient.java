package blinov_4;
import java.io.*;
import java.net.*;

public class SonetClient {

	public static void main(String[] args) {
		Socket socket = null;
		String message = null;
		try{
			socket = new Socket(InetAddress.getLocalHost(),9080);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while((message = br.readLine())!=null){
			System.out.println(message);
			}
		}catch(IOException e){
			System.err.println(e);
		}

	}

}
