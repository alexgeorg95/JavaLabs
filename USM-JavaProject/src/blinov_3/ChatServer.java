package blinov_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private List<ClientsConnected> clients = new ArrayList<>(); // список всех клиентов
	    public ChatServer() {
	    	try{
	    		ServerSocket server = new ServerSocket(8060);
	    		
	    		while(true){
	    			Socket socket = server.accept();
	    			System.out.println(socket.getInetAddress().getHostName()+"connected");
	    			
	    			ClientsConnected client = new ClientsConnected(socket);
	    			clients.add(client);
	    			client.start();
	    			    			
	    		}
	    	} catch(IOException e) {
	    		System.err.println(e);
	    	}
	    }
// класс, обрабатывающий каждое подключение в отдельном потоке
private class ClientsConnected extends Thread {
	private PrintWriter os;
	private BufferedReader is;
	private String name ="";
	private Socket sock;
	public ClientsConnected(Socket s) throws IOException{
		sock = s;
		os = new PrintWriter(s.getOutputStream());
		is = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	@Override
	public void run() {
		String str;
		try {
			name = is.readLine();
			for (ClientsConnected c :clients){
				c.send("@" + name + ":entered the chat"); //сообщить всем, что пользователь вошел в чат
			}
			while((str = is.readLine())!=null) {
				if(str.equals("exit")) break;
				for (ClientsConnected c :clients){
				c.send("@" + name + ":" + str); // отправить сообщение всем клиентам в чате
				}
			}
			for (ClientsConnected c :clients){
				send("@" + name  + ":left the chat"); // сообщить всем,что пользователь покинул чат
			}
		} catch(IOException e) {
			System.err.println("Disconnect");
			
		} finally {
			disconnect();
		}
	}
	public void send(String s) throws IOException {
		os.println(s);
		os.flush();
	}
	public void disconnect(){
		try{
			if(os!=null){
				os.close();
			}
			if(is!=null){
				is.close();
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			this.interrupt();
		}
	}
}
}
